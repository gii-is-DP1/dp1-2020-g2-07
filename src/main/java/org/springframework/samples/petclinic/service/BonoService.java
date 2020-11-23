package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.repository.BonoRepository;
import org.springframework.stereotype.Service;

@Service
public class BonoService {
	@Autowired
	BonoRepository bonorepository;
	
	public Collection<Bono> findAll(){
		return bonorepository.findAll();
	}
	
	public Optional<Bono> findById(int id){
		return bonorepository.findById(id);
	}
	
	public void delete(Bono bono) {
		bonorepository.deleteById(bono.getId());
	}
	
	public void save(@Valid Bono bono) {
		bonorepository.save(bono);
	}
}
