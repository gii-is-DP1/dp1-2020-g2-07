package org.springframework.samples.petclinic.service;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Collection<Employee> findAll(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> findById(int id){
        return employeeRepo.findById(id);
    }

    public List<Employee> findEmployeeByProfession(String profession){
        Profession prof = Profession.valueOf(profession);
        return employeeRepo.findAll().stream().filter(e -> e.getProfession().equals(prof)).collect(Collectors.toList());
    }

    public void save(@Valid Employee employee){
        employeeRepo.save(employee);
    }

    public void delete(Employee employee) {
        employeeRepo.deleteById(employee.getId());
    }

    public void addSalaryToEmployee(int id, EmployeeRevenue salary){
        employeeRepo.findById(id).get().addSalary(salary);
        this.save(employeeRepo.findById(id).get());
    }
    
    public void addTimeTableToEmployee(int id, Horario horario){
        employeeRepo.findById(id).get().addTimeTable(horario);
        this.save(employeeRepo.findById(id).get());
    }
    
    public Set<LocalDate> selectFechasEmpleado(int id){
    	Collection<Horario> hl = employeeRepo.getHorariosByEmployee(id);
    	Set<LocalDate> fechas = new HashSet<>();
    	fechas.addAll(hl.stream().map(x->x.getFecha()).distinct().collect(Collectors.toSet()));
    	return fechas;
    }

}
