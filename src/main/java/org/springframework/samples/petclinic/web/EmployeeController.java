package org.springframework.samples.petclinic.web;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    public static final String EMPLOYEES_LISTING = "employees/employeesListing";
    public static final String EMPLOYEES_FORM = "employees/createOrUpdateEmployeeForm";

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
            BeanUtils.copyProperties(modifiedEmployee, employee.get(), "categoria");
            employeeService.save(employee.get());
            model.addAttribute("message", "Employee updated succesfully!!");
            return "redirect:/employees/" + employee.get().getId();
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

    @GetMapping("/{employeeId}")
    public ModelAndView showEmployee(@PathVariable("employeeId") int employeeId) {
        ModelAndView mav = new ModelAndView("employees/employeeDetails");
        mav.addObject(this.employeeService.findById(employeeId).get());
        return mav;
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
            revenue.setQuantity();
            employeeService.addSalaryToEmployee(employeeId, revenue);

            return "redirect:/employees/" + String.valueOf(employeeId);
        }
    }
}
