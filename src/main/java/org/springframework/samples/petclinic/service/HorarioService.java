package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.stereotype.Service;

@Service
public class HorarioService {

    
    private HorarioRepository horarioRepo;
    
    @Autowired
    public HorarioService(HorarioRepository horarioRepo) {
		this.horarioRepo = horarioRepo;
	}
    
    
	public Collection<Horario> findAll(){
        return horarioRepo.findAll();
    }
    public Optional<Horario> findById(Integer id){
        return horarioRepo.findById(id);
    }
    @Transactional
    public void delete(Horario horario) {
         horarioRepo.deleteById(horario.getId());
    }
    @Transactional
    public void save(@Valid Horario horario) {
         horarioRepo.save(horario);
    }
        
    public void addSesion(int id, Sesion s){
        horarioRepo.findById(id).get().addSesion(s);
        this.save(horarioRepo.findById(id).get());
    }
    
    public Collection<Sesion> findSesionesHorario(int id){
    	return horarioRepo.getSesionByHorario(id);
    }
    
    public Collection<Sesion> activeSessions(int id, Cliente c){
    	Collection<Sesion> a = horarioRepo.getSesionBySala(id);
    	a.removeIf(x->x.getHorario().getFecha().isBefore(LocalDate.now())||x.getSala().getAforo()<=x.getCitas().size());
    	List<Sesion> toRemove = new ArrayList<Sesion>();
    	for(Sesion s:a) {
    		if(!s.getCitas().isEmpty()) {
	    		for(Cita cita:s.getCitas()) {
	    			if(cita.getCliente().equals(c)) {
	    				toRemove.add(s);
	    				break;
	    			}
	    		}
    		}
    	}
    	a.removeAll(toRemove);
    	return a;
    }
    
    public Collection<Horario> futureDays(int employee_id){
    	Collection<Horario> a = horarioRepo.getHorariosByEmployee(employee_id);
    	a.removeIf(x->x.getFecha().isBefore(LocalDate.now()));
    	List<Horario> future = new ArrayList<>(a);
    	Collections.sort(future, (x,y)->x.getFecha().compareTo(y.getFecha()));
    	return future;
    }
    
    public Collection<Horario> pastDays(int employee_id){
    	Collection<Horario> a = horarioRepo.getHorariosByEmployee(employee_id);
    	a.removeIf(x->x.getFecha().isAfter(LocalDate.now()));
    	List<Horario> past = new ArrayList<>(a);
    	Collections.sort(past, (x,y)->x.getFecha().compareTo(y.getFecha()));
    	return past;
    }
    
    public boolean checkDuplicatedSessions(Sesion s, int horarioId) {
    	boolean duplicated = false;
    	for(Horario h:horarioRepo.findAll()) {
    		if(h.getFecha().equals(s.getHorario().getFecha())) {
    			for(Sesion sc:h.getSesiones()) {
    				boolean checkprevio = s.getHoraInicio().isBefore(sc.getHoraInicio()) && s.getHoraFin().isBefore(sc.getHoraInicio().plusMinutes(1));
    				boolean checkpost = s.getHoraInicio().isAfter(sc.getHoraFin().minusMinutes(1)) && s.getHoraFin().isAfter(sc.getHoraFin());
    				if(sc.getSala().equals(s.getSala())&&!(checkprevio||checkpost)) {
    					duplicated=true;
    					break;	
    				}
    			}
    		}
    		if(duplicated) {
    			break;
    		}
    	}
    	return duplicated;
    }
}
