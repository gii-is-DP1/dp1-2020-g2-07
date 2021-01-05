package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sala;

public interface SalaRepository extends Repository<Sala, Integer>, CrudRepository<Sala,Integer>{
	
	Collection<Sala> findAll();
	Optional<Sala> findById(int id);
	Optional<Sala> findById(Integer id);
	

}
