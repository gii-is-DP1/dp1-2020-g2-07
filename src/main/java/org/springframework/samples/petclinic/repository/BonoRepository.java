  
package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Bono;

public interface BonoRepository extends Repository<Bono, Integer>, CrudRepository<Bono,Integer>{
	Collection<Bono> findAll();

	List<Bono> findById(int id);

	void deleteById(Integer id);
}