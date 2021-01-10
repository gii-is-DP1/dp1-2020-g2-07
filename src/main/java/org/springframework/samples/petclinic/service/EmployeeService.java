package org.springframework.samples.petclinic.service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeRepository employeeRepo, UserService userService, AuthoritiesService authoritiesService){
        this.employeeRepo = employeeRepo;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

    private EmployeeRepository employeeRepo;

    private UserService userService;

    private AuthoritiesService authoritiesService;

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
        employee.getUser().setEnabled(true);
        userService.saveUser(employee.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(employee.getUser().getUsername(), "employee");

    }

    public void delete(Employee employee) {
        employeeRepo.deleteById(employee.getId());
        userService.delete(employee.getUser());
    }

    public void addSalaryToEmployee(int id, EmployeeRevenue salary){
        employeeRepo.findById(id).get().addSalary(salary);
        this.save(employeeRepo.findById(id).get());
    }
    
    public void addScheduletoEmployee(int id, Horario schedule) {
    	employeeRepo.findById(id).get().addHorario(schedule);
    	 this.save(employeeRepo.findById(id).get());
    }
    
    public Optional<Employee> findEmployeeByUsername(String username){
        return this.findAll().stream().filter(c -> c.getUser().getUsername().equals(username)).findAny();
    }

    public Optional<Employee> employeeByUsername(String username){
        return this.findAll().stream().filter(e -> e.getUser().getUsername().equals(username)).findAny();
    }

}
