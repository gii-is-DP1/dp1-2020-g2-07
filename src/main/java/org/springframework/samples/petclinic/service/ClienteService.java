package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;

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
        userService.delete(cliente.getUser());
    }

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


    public void addPayToClient(int id, Pago pay){
        clientRepo.findById(id).get().addPay(pay);
        this.save(clientRepo.findById(id).get(), "edit");
    }
    
    public Cliente findClienteByUsername(String username){
    	for(Cliente c: clientRepo.findAll()) {
    		String usernamerepo = c.getUser().getUsername();
    		if(usernamerepo.equals(username)) {
    			return c;
    		}
    	}
    	return null;
    }

}