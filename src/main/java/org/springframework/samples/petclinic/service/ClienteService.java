package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clientRepo;

    public Collection<Cliente> findAll(){
        return clientRepo.findAll();
    }
}
