package org.springframework.samples.petclinic.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import java.util.Collection;


public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    Collection<Cliente> findAll();
}
