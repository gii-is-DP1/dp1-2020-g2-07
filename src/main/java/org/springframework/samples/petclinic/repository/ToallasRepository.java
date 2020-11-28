package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Toallas;

public interface ToallasRepository extends CrudRepository<Toallas, Integer>{
	Collection<Toallas> findAll();
}
