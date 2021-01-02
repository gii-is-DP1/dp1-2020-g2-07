package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    
    CitaRepository citaRepo;
    ClienteService cls;
    
    @Autowired
    public CitaService(CitaRepository citaRepo, ClienteService cls) {
    	this.citaRepo = citaRepo;
    	this.cls = cls;
    }
    

    public Collection<Cita> findAll(){
        return citaRepo.findAll();
    }
	
	public Optional<Cita> findById(Integer id){
    	return citaRepo.findById(id);
    }
	
	@Transactional
	public void delete(Cita c) {
		citaRepo.deleteById(c.getId());
    }
    
	@Transactional
    public void save(@Valid Cita c){
    	citaRepo.save(c);
    }
}
