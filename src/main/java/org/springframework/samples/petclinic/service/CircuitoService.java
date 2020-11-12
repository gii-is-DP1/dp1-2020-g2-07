package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.repository.CircuitoRepository;
import org.springframework.stereotype.Service;

@Service
public class CircuitoService {
	
	@Autowired
	CircuitoRepository circuitoRepo;
	
	public Collection<Circuito> findAll(){
        return circuitoRepo.findAll();
    }
	
	public Optional<Circuito> findById(int id){
    	return circuitoRepo.findById(id);
    }
	
	public void delete(Circuito c) {
		circuitoRepo.deleteById(c.getId());
    }
    
    public void save(@Valid Circuito c) {
    	circuitoRepo.save(c);
    }
    

}
