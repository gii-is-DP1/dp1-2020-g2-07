package org.springframework.samples.petclinic.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    public static final String EMPLOYEES_LISTING = "employees/employeesListing";
    public static final String EMPLOYEES_FORM = "employees/createOrUpdateEmployeesForm";

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String listEmployees(ModelMap model){
        model.addAttribute("employees", employeeService.findAll());
        return EMPLOYEES_LISTING;
    }

    @GetMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") int id, ModelMap model){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()){
            model.addAttribute("employee", employee.get());
            return EMPLOYEES_FORM;
        }else{
            model.addAttribute("message", "Cant find the employee you are looking for");
            return listEmployees(model);
        }
    }

    @PostMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") int id, ModelMap model, @Valid Employee modifiedEmployee, BindingResult binding){
        Optional<Employee> employee = employeeService.findById(id);
        if(binding.hasErrors()){
            return EMPLOYEES_FORM;
        }else{
            BeanUtils.copyProperties(modifiedEmployee, employee.get(), "{id, categoria}");
            employeeService.save(employee.get());
            model.addAttribute("message", "Employee updated succesfully!!");
            return listEmployees(model);
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") int id, ModelMap model){
        Optional<Employee> employee = employeeService.findById(id);
        if(employee.isPresent()){
            employeeService.delete(employee.get());
            model.addAttribute("message", "Employee deleted successfully!!");
            return listEmployees(model);
        }else {
            model.addAttribute("message", "Cant find the employee you are looking for");
            return listEmployees(model);
        }
    }

}
