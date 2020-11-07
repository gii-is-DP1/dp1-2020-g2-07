package org.springframework.samples.petclinic.repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Cliente;

import java.util.Collection;

public interface ClienteRepository extends Repository<Cliente, Integer> {

	Collection<Cliente> findAll();
    
	void save(Cliente cliente) throws DataAccessException;
	
    @Query("SELECT cliente FROM Cliente cliente WHERE cliente.id =:id")
	public Cliente findById(@Param("id") int id);
}
