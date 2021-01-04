package org.springframework.samples.petclinic.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete(Cliente cliente) {
        clientRepo.deleteById(cliente.getId());
        userService.delete(cliente.getUser());
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
        }

        clientRepo.save(cliente);

        userService.saveUser(cliente.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "client");

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
