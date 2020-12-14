package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.repository.HorarioRepository;
import org.springframework.stereotype.Service;

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
}