package org.springframework.samples.petclinic.service;

import java.time.Month;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    ClienteRepository clientRepo;

    private UserService userService;

    private EmailService emailService;

    private AuthoritiesService authoritiesService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, UserService userService, AuthoritiesService authoritiesService,
                          EmailService emailService){
        this.clientRepo = clienteRepository;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
        this.emailService = emailService;
    }

    public Collection<Cliente> findAll(){
        return clientRepo.findAll();
    }

    public Optional<Cliente> findById(int id){
        return clientRepo.findById(id);
    }

    public Collection<Cliente> findClientByBirthdayMonth(Month month) {
        return this.findAll()
            .stream()
            .filter(u -> u.getDOB().getMonth().equals(month))
            .collect(Collectors.toList());
    }

    public void delete(Cliente cliente) {
        clientRepo.deleteById(cliente.getId());
        userService.delete(cliente.getUser());
        log.info(String.format("Client with username %s and ID %d has been deleted", cliente.getUser().getUsername(), cliente.getId()));
    }

    @Transactional
    public void save(@Valid Cliente cliente, String type_safe){
        if(!type_safe.equals("edit")){
            Date date = java.util.Calendar.getInstance().getTime();

            Email e = new Email();
            String[] addres = {"spa.dp2020@gmail.com"};
            e.setAddress(addres);
            e.setSubject("Sign up petition made on " + date.toString());
            e.setBody("Have a petition of registrer from " + cliente.getFirst_name() + " " + cliente.getLast_name());
            emailService.sendMail(e);
            cliente.getUser().setEnabled(false);
//            cliente.setCitas(new HashSet<Cita>());
            clientRepo.save(cliente);
            userService.saveUser(cliente.getUser());
            authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "client");

            log.info(String.format("New client with username %s and ID %d has been created", cliente.getUser().getUsername(), cliente.getId()));
        }else{
            cliente.getUser().setEnabled(cliente.getUser().isEnabled());
            clientRepo.save(cliente);
            userService.saveUser(cliente.getUser());

            log.info(String.format("Client with username %s and ID %d has been edited", cliente.getUser().getUsername(), cliente.getId()));
        }
    }

    @Transactional
    public void addPayToClient(int id, Pago pay){
        clientRepo.findById(id).get().addPay(pay);
        this.save(clientRepo.findById(id).get(), "edit");
    }

    public Optional<Cliente> clientByUsername(String username){
        return this.findAll().stream().filter(c -> c.getUser().getUsername().equals(username)).findAny();
    }
}
