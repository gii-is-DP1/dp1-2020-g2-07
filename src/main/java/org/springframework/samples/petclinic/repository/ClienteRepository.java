package org.springframework.samples.petclinic.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.petclinic.model.PetType;
import java.util.List;
import java.util.Collection;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    Collection<Cliente> findAll();
}
