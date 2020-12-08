package org.springframework.samples.petclinic.repository;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Collection<Employee> findAll();

    Optional<Employee> findById(int id);

    Collection<Employee> findEmployeeByProfession(String prefession);

    @Query("SELECT revenue FROM EmployeeRevenue revenue WHERE revenue.employee.id = :employee_id")
    public Collection<EmployeeRevenue> getSalariesByEmployee(@Param("employee_id") int employee_id);
    
    @Query("SELECT horario FROM Horario horario WHERE horario.employee.id = :employee_id")
    public Collection<Horario> getHorariosByEmployee(@Param("employee_id") int employee_id);
    
    @Query("SELECT sesion FROM Sesion sesion WHERE sesion.horario.id = :horario_id")
    public Collection<Sesion> getSesionByHorario(@Param("horario_id") int horario_id);

}
