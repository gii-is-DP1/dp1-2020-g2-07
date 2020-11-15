package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Employee;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Collection<Employee> findAll();

    Optional<Employee> findById(int id);

    Collection<Employee> findEmployeeByProfession(String prefession);

}
