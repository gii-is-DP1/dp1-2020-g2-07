package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Balance;

public interface BalanceRepository  extends CrudRepository<Balance,Integer>{
    Collection<Balance> findAll();
}
