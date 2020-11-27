package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Employee;

public interface EmployeeRepository extends Repository<Employee,Integer>, CrudRepository<Employee, Integer> {
    Collection<Employee> findAll();

    Collection<Employee> findEmployeeByProfession(String prefession);

    List<Employee> findById(int id);
}
