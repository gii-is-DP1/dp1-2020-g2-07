package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Sala;

public interface SalaRepository extends Repository<Sala, Integer>, CrudRepository<Sala,Integer>{
	
	Collection<Sala> findAll();
	Optional<Sala> findById(int id);
	Optional<Sala> findById(Integer id);
	

}
