package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.CircuitoRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CircuitoService {
	
	 @Autowired
	 CircuitoRepository circuitoRepo;
	
	@Autowired
	public CircuitoService(CircuitoRepository circuitoRepo) {
		this.circuitoRepo = circuitoRepo;

	}
	
	public Collection<Circuito> findAll(){
        return circuitoRepo.findAll();
    }
	
	public Optional<Circuito> findById(Integer id){
    	return circuitoRepo.findById(id);
    }
	
	public void delete(Circuito c) {
		circuitoRepo.deleteById(c.getId());
    }
    
    public void save(@Valid Circuito c) {
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

	public List<Circuito> findByIdLista(int id) {
		return circuitoRepo.findById(id);
	}
	public Circuito getCircuitwithIdDIfferent(String name,Integer id) {
			name=name.toLowerCase();
			for (Circuito circuito: getCircuits()) {
				String compName=circuito.getName();
				compName=compName.toLowerCase();
				if (compName.equals(name) && circuito.getId()!=id) {
					return circuito;
				}
			}
			return null;
		}
	    public Collection<Circuito> getCircuits(){
	    	return findAll();
	    }
//	    public Circuito getCircuitwithIdDIfferentAforoNull(String name,Integer id) {
//			name=name.toLowerCase();
//			for (Circuito circuito: getCircuits()) {
//				String compName=circuito.getName();
//				compName=compName.toLowerCase();
//				if (compName.equals(name) && circuito.getId()!=id) {
//					if(circuito.getAforo()==null)
//						return circuito;
//				}
//			}
//			return null;
//		}
	  
	    @Transactional(rollbackOn = DuplicatedCircuitoNameException.class)
	    public void saveCircuito(Circuito circuito)  throws DuplicatedCircuitoNameException {
	    	Circuito otherCircuit=getCircuitwithIdDIfferent(circuito.getName(), circuito.getId());
//	    	Circuito otherCircuitAforoNull=getCircuitwithIdDIfferentAforoNull(circuito.getName(), circuito.getId());
	    	 if (StringUtils.hasLength(circuito.getName())&&  (otherCircuit!= null && otherCircuit.getId()!=circuito.getId())) {            	
	         	throw new DuplicatedCircuitoNameException();
	         }else
	        	 circuitoRepo.save(circuito);    
	    }
	    	
	


}
