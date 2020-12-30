package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
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
import org.springframework.web.servlet.ModelAndView;

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
        if (user.equals(null))
            return false;
        else
            return empleado.get().getUser().equals(user) || user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"));
    }

    @GetMapping
    public String listEmployees(ModelMap model){
        model.addAttribute("employees", employeeService.findAll());
        return EMPLOYEES_LISTING;
    }

    @GetMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") int id, ModelMap model, Authentication auth){
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
        return listEmployees(model);
    }

    @PostMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") int id, ModelMap model, @Valid Employee modifiedEmployee, BindingResult binding){
        Optional<Employee> employee = employeeService.findById(id);
        modifiedEmployee.setCategory(employee.get().getCategory());
        BeanUtils.copyProperties(modifiedEmployee, employee.get(), "id,category");
        employeeService.save(employee.get());
        model.addAttribute("message","Se ha modificado el empleado");
        return listEmployees(model);
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

    @GetMapping("/{employeeId}")
    public ModelAndView showEmployee(@PathVariable("employeeId") int employeeId, Authentication auth) {
        Optional<Employee> employee = employeeService.findById(employeeId);

        if (hasAuthority(employee, userService.findUser(auth.getName()).get())) {
            ModelAndView mav = new ModelAndView("employees/employeeDetails");
            mav.addObject(this.employeeService.findById(employeeId).get());
            //placeholder de las horas totales, es solo para comprobar que funciona dandole los parametros
            mav.addObject("horas", this.employeeService.findById(employeeId).get().getHoursWorked(LocalDate.of(2020, 12, 1), LocalDate.of(2020, 12, 31)));
            return mav;
        }
        else {
            return new ModelAndView("/login");
        }
    }

    @GetMapping("/new")
    public String editNewEmployee(ModelMap model) {
        model.addAttribute("employee",new Employee());
        return EMPLOYEES_FORM;
    }

    @PostMapping("/new")
    public String saveNewEmployee(@Valid Employee employee, BindingResult binding,ModelMap model) {
        if(binding.hasErrors()) {
            return EMPLOYEES_FORM;
        }else {
            employee.setCategory(Categoria.EMPLEADO);
            employeeService.save(employee);
            model.addAttribute("message", "The employee was created successfully!");
            return "redirect:/employees/" + employee.getId();
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
        model.addAttribute("employee",employeeService.findById(employeeId).get());
        if(binding.hasErrors()){
            model.addAttribute("message", "hay un error capo");
            return "salary/salaryForm";
        }else{
            revenue.setEmployee(employeeService.findById(employeeId).get());
            employeeService.addSalaryToEmployee(employeeId, revenue);

            return "redirect:/employees/" + String.valueOf(employeeId);
        }
    }
     
    
}