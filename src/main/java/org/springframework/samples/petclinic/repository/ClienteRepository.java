package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cliente;


public interface ClienteRepository extends Repository<Cliente,Integer>, CrudRepository<Cliente, Integer> {
    Collection<Cliente> findAll();

    Optional<Cliente> findById(Integer id);

    void deleteById(Integer id);
    
    List<Cliente> findById( int id);
}
