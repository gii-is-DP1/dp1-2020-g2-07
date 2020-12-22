package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.SalaRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SalaService {
	
    @Autowired
    SalaRepository salaRepo;

    public Collection<Sala> findAll(){
        return salaRepo.findAll();
    }
    
    public Optional<Sala> findById(Integer id){
    	return salaRepo.findById(id);
    }
    
    public List<Sala> findByIdLista(int id){
    	return salaRepo.findById(id);
    }
    
    @Transactional
    public void delete(Sala sala) {
    	salaRepo.deleteById(sala.getId());
    }
    
    @Transactional
    public void save(@Valid Sala sala) {
    	salaRepo.save(sala);
    }
    
    public Sala getRoomwithIdDIfferent(String name,Integer id) {
		name=name.toLowerCase();
		for (Sala sala: getRooms()) {
			String compName=sala.getName();
			compName=compName.toLowerCase();
			if (compName.equals(name) && sala.getId()!=id) {
				return sala;
			}
		}
		return null;
	}
    public Collection<Sala> getRooms(){
    	return salaRepo.findAll();
    }
    
    @Transactional(rollbackOn = DuplicatedSalaNameException.class)
    public void saveSala(Sala sala)  throws DuplicatedSalaNameException {
    	Sala otherRoom=getRoomwithIdDIfferent(sala.getName(), sala.getId());
    	 if (StringUtils.hasLength(sala.getName()) &&  (otherRoom!= null && otherRoom.getId()!=sala.getId())) {            	
         	throw new DuplicatedSalaNameException();
         }else
        	 salaRepo.save(sala);    
    }
    	
    	
    }
    

