package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cita;

public interface CitaRepository extends CrudRepository<Cita,Integer>{
	
	Collection<Cita> findAll();

}
