package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepo;

    public Collection<Cita> findAll(){
        return citaRepo.findAll();
    }
	
}
