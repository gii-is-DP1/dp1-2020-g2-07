package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Collection<Employee> findAll();

    Optional<Employee> findById(int id);

    Collection<Employee> findEmployeeByProfession(String prefession);

}
