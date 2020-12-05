package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clientRepo;

    @Autowired
    private UserService userService;

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
        clientRepo.save(cliente);

        userService.saveUser(cliente.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "owner");

    }

    public void addPayToClient(int id, Pago pay){
        clientRepo.findById(id).get().addPay(pay);
        this.save(clientRepo.findById(id).get());
    }

}
