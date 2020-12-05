  
package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Cliente;
import java.util.Collection;
import java.util.Optional;


public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Optional<Cliente> findById(Integer id);

    void deleteById(Integer id);
    
    Optional<Cliente> findById( int id);

    Collection<Cliente> findAll();

}
