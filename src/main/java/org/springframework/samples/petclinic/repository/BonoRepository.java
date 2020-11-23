package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Bono;

public interface BonoRepository extends CrudRepository<Bono, Integer> {
	Collection<Bono> findAll();

}
