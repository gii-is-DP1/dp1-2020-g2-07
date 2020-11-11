package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.SalaRepository;
import org.springframework.stereotype.Service;


@Service
public class SalaService {
	
    @Autowired
    SalaRepository salaRepo;

    public Collection<Sala> findAll(){
        return salaRepo.findAll();
    }

}
