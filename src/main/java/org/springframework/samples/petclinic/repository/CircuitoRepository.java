package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Circuito;

public interface CircuitoRepository extends CrudRepository<Circuito, Integer> {
		
	Collection<Circuito> findAll();
}
