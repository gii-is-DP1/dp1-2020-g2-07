package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Horario;

public interface HorarioRepository extends CrudRepository<Horario,Integer>{
	
	Collection<Horario> findAll();

}
