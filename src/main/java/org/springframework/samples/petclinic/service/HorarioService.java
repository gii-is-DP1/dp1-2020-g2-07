package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import javassist.expr.NewArray;

@Service
public class HorarioService {

    @Autowired
    HorarioRepository horarioRepo;

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
     
    
//    public Collection<Sesion> findSesionesSala(int id){
//    	return horarioRepo.getSesionBySala(id);
//    }
    
    public Collection<Sesion> activeSessions(int id){
    	Collection<Sesion> a = horarioRepo.getSesionBySala(id);
    	a.removeIf(x->x.getHorario().getFecha().isBefore(LocalDate.now())||x.getSala().getAforo()<=x.getCitas().size());
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
    

}
