package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;

public interface CircuitoRepository extends Repository<Circuito, Integer>, CrudRepository<Circuito, Integer> {
		
	Collection<Circuito> findAll();
	Optional<Circuito> findById(int id);
	Optional<Circuito> findById(Integer id);
	
	@Query("SELECT salas FROM Circuito circuito WHERE circuito.circuito.id = :circuito_id")
    public Collection<Sala> getRoomsByCircuito(@Param("circuito_id") int circuito_id);
	
//	@Query("SELECT salas")
}
