package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clientRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthoritiesService authoritiesService;

    public Collection<Cliente> findAll(){
        return clientRepo.findAll();
    }

    public Optional<Cliente> findById(int id){
        return clientRepo.findById(id);
    }

    public void delete(Cliente cliente) {
        clientRepo.deleteById(cliente.getId());
    }

    public void save(@Valid Cliente cliente){
        Date date = java.util.Calendar.getInstance().getTime();

        clientRepo.save(cliente);

        cliente.getUser().setEnabled(false);
        userService.saveUser(cliente.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "owner");

        Email e = new Email();
        e.setAddress("spa.dp2020@gmail.com");
        e.setSubject("Register petition made on " + date.toString());
        e.setBody("Have a petition of registrer from " + cliente.getFirst_name() + " " + cliente.getLast_name());

        emailService.sendMail(e);
    }

    public void addPayToClient(int id, Pago pay){
        clientRepo.findById(id).get().addPay(pay);
        this.save(clientRepo.findById(id).get());
    }

}
