package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Sala;

public interface SalaRepository extends CrudRepository<Sala, Integer>{
	
	Collection<Sala> findAll();

}
