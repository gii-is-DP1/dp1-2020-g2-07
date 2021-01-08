package org.springframework.samples.petclinic.service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.SubType;
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
    
    public Collection<Sesion> inTimeSessions(Collection<Sesion> available_sessions,Cliente c){ //Sessions deleting the ones the client can´t access due to they subscription type
    	List<Sesion> toRemove = new ArrayList<Sesion>();
    	SubType sub_type = c.getSuscripcion();
    	if(sub_type.toString().equals("MORNING")) {
    		for(Sesion s: available_sessions) {
    			if((s.getHoraInicio().isAfter(LocalTime.parse("14:00")) || s.getHoraInicio().equals(LocalTime.parse("14:00")))  && (s.getHoraFin().isBefore(LocalTime.parse("21:00")) || s.getHoraFin().equals(LocalTime.parse("21:00")))) {
    				toRemove.add(s);
    			}
    		}
    	}else if(sub_type.toString().equals("AFTERNOON")) {
    		for(Sesion s: available_sessions) {
    			if((s.getHoraInicio().isAfter(LocalTime.parse("09:00")) || s.getHoraInicio().equals(LocalTime.parse("09:00")) )  && (s.getHoraFin().isBefore(LocalTime.parse("14:00")) || s.getHoraFin().equals(LocalTime.parse("14:00"))) ){
    				toRemove.add(s);
    			}
    		}
    	}
    	available_sessions.removeAll(toRemove);
    	return available_sessions;
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
    
    public List<LocalTime> initHours(){
    	int gapInMinutes = 60;
    	int loops = ((int) Duration.ofHours(12).toMinutes() / gapInMinutes);
    	List<LocalTime> times_op = new ArrayList<>( loops );
    	LocalTime time = LocalTime.parse("09:00");
    	for( int i = 1 ; i <= loops ; i ++ ) {
        	    times_op.add( time );
        	    time = time.plusMinutes( gapInMinutes ) ;
    	}
    	return times_op;
    }
    
    public List<LocalTime> endHours(){
    	int gapInMinutes = 60;
    	int loops = ((int) Duration.ofHours(12).toMinutes() / gapInMinutes);
    	List<LocalTime> times_end = new ArrayList<>( loops );
    	LocalTime time_end = LocalTime.parse("10:00");
    	for( int i = 1 ; i <= loops ; i ++ ) {
    			times_end.add( time_end );
        	    time_end = time_end.plusMinutes( gapInMinutes ) ;
    	}
    	return times_end;
    }
    
    public Boolean dayAlreadyInSchedule(Employee e,Horario horario) {
    	Boolean res = false;
    	for (Horario h : e.getHorarios()) {
    		if(horario.getFecha().equals(h.getFecha())) {
    			res=true;
    		}
    	}
		return res;
    }
    public Boolean checkTokenAptExist(Cita apt, Set<Cita> set) {
    	Cliente client = apt.getCliente();
    	boolean ok = false;
    	for(Cita c : set) {
    		if(!client.equals(c.getCliente())) {
    			break;
    		}
    		Sesion s = apt.getSesion();
    		Sesion sc = c.getSesion();
    		boolean checkprevio = s.getHoraInicio().isBefore(sc.getHoraInicio()) && s.getHoraFin().isBefore(sc.getHoraInicio().plusMinutes(1));
    		boolean checkpost = s.getHoraInicio().isAfter(sc.getHoraFin().minusMinutes(1)) && s.getHoraFin().isAfter(sc.getHoraFin());
    		if(!(checkprevio||checkpost)) {
    			ok=true;
    			break;
    		}
    	}
		return ok;
    }
}
