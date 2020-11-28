package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Sala;

public interface SalaRepository extends Repository<Sala, Integer>, CrudRepository<Sala,Integer>{
	
	Collection<Sala> findAll();
	List<Sala> findById(int id);

}
