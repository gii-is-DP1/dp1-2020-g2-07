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
	
	@Query("SELECT sala FROM Sala sala ORDER BY sala.name")
    public Collection<Sala> getRoomsByCircuito();
	

}
