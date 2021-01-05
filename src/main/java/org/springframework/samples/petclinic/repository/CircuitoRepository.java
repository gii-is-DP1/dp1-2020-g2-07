package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Circuito;

public interface CircuitoRepository extends Repository<Circuito, Integer>, CrudRepository<Circuito, Integer> {
		
	Collection<Circuito> findAll();
	Optional<Circuito> findById(int id);
	Optional<Circuito> findById(Integer id);
}
