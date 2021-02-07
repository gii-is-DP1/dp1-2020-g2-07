package org.springframework.samples.petclinic.web;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    public static final String EMPLOYEES_LISTING = "employees/employeesListing";
    public static final String EMPLOYEES_FORM = "employees/createOrUpdateEmployeeForm";

    @Autowired
    EmployeeService employeeService;
    @Autowired
    SalaService salaService;
    @Autowired
    HorarioService horarioService;
    @Autowired
    UserService userService;

    public Boolean hasAuthority(Optional<Employee> empleado, User user) {
        if (user.equals(null)||empleado.equals(null))
            return false;
        else
            return empleado.get().getUser().equals(user) || user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"));
    }

    @GetMapping
    public String listEmployees(ModelMap model, Authentication auth){
        if (auth.isAuthenticated()) {
            Optional<Employee> e = employeeService.findEmployeeByUsername(auth.getName());
            if(e.isPresent()) {
            	return "redirect:/employees/"+String.valueOf(e.get().getId());
            }else model.addAttribute("employees", employeeService.findAll());

            return EMPLOYEES_LISTING;
        }
        else return "redirect:http://localhost/";
    }

    public String listEmployees(ModelMap model){
        model.addAttribute("employees", employeeService.findAll());
        return EMPLOYEES_LISTING;
    }

    @GetMapping("/{employeeId}/edit")
    public String editEmployee(@PathVariable("employeeId") int id, ModelMap model, Authentication auth){
        Optional<Employee> employee = employeeService.findById(id);
        User user = userService.findUser(auth.getName()).get();
        if(employee.isPresent() && hasAuthority(employee, user)) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("profession", employee.get().getProfession());
            return EMPLOYEES_FORM;
        }
        else if (!hasAuthority(employee, user))
            model.addAttribute("message","Acceso denegado");
        else
            model.addAttribute("message", "Cant find the employee you are looking for");
        return listEmployees(model,auth);
    }

    @PostMapping("/{employeeId}/edit")
    public String editEmployee(@PathVariable("employeeId") int id, ModelMap model, @Valid Employee modifiedEmployee,
    		BindingResult binding,@RequestParam(value="version", required=false) Integer version){
        Optional<Employee> employee = employeeService.findById(id);
        if(binding.hasErrors()){
            log.warn(String.format("Employee with username %s and ID %d wasn't able to be updated",
                employee.get().getUser().getUsername(), employee.get().getId()));
            return EMPLOYEES_FORM;
        }else if(employee.get().getVersion()!=version) {
        	model.addAttribute("message", "Concurrent modification of client, try again later");
        	return listEmployees(model);
        }
        else{
            BeanUtils.copyProperties(modifiedEmployee, employee.get(), "id","category");
            employeeService.save(employee.get());
            model.addAttribute("message", "Employee updated succesfully!!");
            return "redirect:/employees/" + employee.get().getId();
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") int id, ModelMap model){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()){
        	if(employee.get().getHorarios().size()!=0) {
        		model.addAttribute("message", "The employee can´t be deleted if they have work left to do");
        	}else {
                employeeService.delete(employee.get());
                model.addAttribute("message", "Employee deleted successfully!!");
        	}
        }else {
            model.addAttribute("message", "Cant find the employee you are looking for");
        }
        return listEmployees(model);
    }

    @GetMapping("/{employeeId}")
    public String showEmployee(@PathVariable("employeeId") int employeeId, Authentication auth,ModelMap model) {
        Optional<Employee> employee = employeeService.findById(employeeId);
        if (employee.isPresent()){
            if (hasAuthority(employee, userService.findUser(auth.getName()).get())) {
                model.addAttribute("employee", employee.get());
                model.addAttribute("horarios",horarioService.calcDays(employeeId,"future"));
                return "employees/employeeDetails";
            }else{
                model.addAttribute("message", "You aren't allowed");
                return listEmployees(model,auth);
            }
        }else{
            model.addAttribute("message","That employee doesn't exist");
            return listEmployees(model,auth);
        }
    }

    @GetMapping("/new")
    public String editNewEmployee(ModelMap model) {
        model.addAttribute("employee",new Employee());
        return EMPLOYEES_FORM;
    }

    @PostMapping("/new")
    public String saveNewEmployee(@Valid Employee employee, BindingResult binding,ModelMap model, Authentication auth) {
        if(binding.hasErrors()) {
            return EMPLOYEES_FORM;
        }else {
            employee.setCategory(Categoria.EMPLEADO);
            employeeService.save(employee);
            model.addAttribute("message", "The employee was created successfully!");
            return listEmployees(model, auth);
        }
    }

    @GetMapping("/{employeeId}/newSalary")
    public String addSalary(@PathVariable("employeeId") int employeeId, ModelMap model) {
        model.addAttribute("employee",employeeService.findById(employeeId).get());
        model.addAttribute("revenue",new EmployeeRevenue());
        return "salary/salaryForm";
    }

    @PostMapping("/{employeeId}/newSalary")
    public String saveSalary(@PathVariable("employeeId") int employeeId,@Valid @ModelAttribute("revenue") EmployeeRevenue revenue, BindingResult binding, ModelMap model){
        Optional<Employee> employee = employeeService.findById(employeeId);
        model.addAttribute("employee",employee.get());
        if(binding.hasErrors() || !revenue.getDateStart().isBefore(revenue.getDateEnd()) || !revenue.getDateStart().getMonth().equals(revenue.getDateEnd().getMonth())){
        	if(!revenue.getDateStart().isBefore(revenue.getDateEnd())) {
        		model.addAttribute("message", "Start date must be before end date");
        	}else if(!revenue.getDateStart().getMonth().equals(revenue.getDateEnd().getMonth())) {
        		model.addAttribute("message", "Revenues must have a length of only 1 month");
        	}else {
        		model.addAttribute("message", "There has been a problem");
                log.warn(String.format("Salary of employee with username %s and ID %d wasn't able to be created",
                    employee.get().getUser().getUsername(), employee.get().getId()));
        	}
            return "salary/salaryForm";
        }else{
        	Integer hours = employeeService.findById(employeeId).get().getHoursWorked(revenue.getDateStart(), revenue.getDateEnd()).intValue();
        	revenue.setHoursWorked(hours);
            revenue.setEmployee(employeeService.findById(employeeId).get());
            employeeService.addSalaryToEmployee(employeeId, revenue);

            return "redirect:/employees/" + String.valueOf(employeeId);
        }
    }


}
