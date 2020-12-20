package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepo;

    public Collection<Cita> findAll(){
        return citaRepo.findAll();
    }
	
	public Optional<Cita> findById(Integer id){
    	return citaRepo.findById(id);
    }
	
	public void delete(Cita c) {
		citaRepo.deleteById(c.getId());
    }
    
    public void save(@Valid Cita c, Cliente cl){
    	c.setCliente(cl);
    	citaRepo.save(c);
    }
}
