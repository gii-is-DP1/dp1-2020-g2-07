package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

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
    
    public Optional<Sala> findById(int id){
    	return salaRepo.findById(id);
    }
    
    public void delete(Sala sala) {
    	salaRepo.deleteById(sala.getId());
    }
    
    public void save(@Valid Sala sala) {
    	salaRepo.save(sala);
    }

}
