package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.CircuitoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//	@Transactional(readOnly = true)
//	public Circuito findCircuitoById(int id) {
//		return circuitoRepo.findById(id);
//	}
//	@Transactional
//	public void saveCircuito(Circuito circuito) throws DataAccessException {
//		//creating circuito
//		circuitoRepo.save(circuito);		
//		//creating user
//		userService.saveUser(owner.getUser());
//		//creating authorities
//		authoritiesService.saveAuthorities(owner.getUser().getUsername(), "owner");
//	}		

	
	public void delete(Circuito c) {
		circuitoRepo.deleteById(c.getId());
    }
    
    public void save(@Valid Circuito c) {
    	circuitoRepo.save(c);
    }
    

}
