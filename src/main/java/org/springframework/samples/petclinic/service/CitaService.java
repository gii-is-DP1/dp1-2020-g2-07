package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.repository.CitaRepository;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    CitaRepository citaRepo;
    
    @Autowired
    public CitaService(CitaRepository citaRepo) {
    	this.citaRepo = citaRepo;
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
		c.getCliente().getCitas().remove(c);
    }
    
	@Transactional
    public void save(@Valid Cita c){
//		c.getCliente().addApointment(c);
    	citaRepo.save(c);
    }
}
