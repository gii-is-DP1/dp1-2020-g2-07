package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cliente;


public interface ClienteRepository extends Repository<Cliente,Integer>, CrudRepository<Cliente, Integer> {
    Collection<Cliente> findAll();

    List<Cliente> findById(int id);
    
    void deleteById(Integer id);
}
