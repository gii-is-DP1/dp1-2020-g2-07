package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.CircuitoRepository;
import org.springframework.stereotype.Service;

@Service
public class CircuitoService {
	
	
	private CircuitoRepository circuitoRepo;
	
	@Autowired
	public CircuitoService(CircuitoRepository circuitoRepo) {
		this.circuitoRepo = circuitoRepo;

	}
	
	public Collection<Circuito> findAll(){
        return circuitoRepo.findAll();
    }
	
	public Optional<Circuito> findById(int id){
    	return circuitoRepo.findById(id);
    }
	
	public void delete(Circuito c) {
		circuitoRepo.deleteById(c.getId());
    }
    
    public void save(@Valid Circuito c){
    	circuitoRepo.save(c);
    }
    
    
    public Integer getAforo(Circuito c) {
    	List<Sala> s = c.getSalas();
    	return aforoCircuito(s);
    }

	private Integer aforoCircuito(List<Sala> salas) {
		Integer min = salas.get(0).getAforo();
		for(int i=1; i< salas.size();i++) {
			Integer aforo = salas.get(i).getAforo();
			if(aforo<min) {
				min = aforo;
			}
		}
		return min;
	}


}
