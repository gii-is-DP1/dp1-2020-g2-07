package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String HOME_ADMIN = "admin/adminHome";
    public static final String ADMIN_EMAIL = "admin/newEmail";
    public static final String ADMIN_ANNOUNCEMENT = "admin/newAnnouncement";
    public static final String ADMIN_USERS = "admin/checkUsers";
    public static final String ADMINS_FORM ="admin/createOrUpdateAdminsForm";
    public static final String ADMIN_BDAYS ="admin/checkBirthdays";

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/new")
    public String editNewAdmin(ModelMap model) {
        model.addAttribute("admin",new Admin());
        model.addAttribute("user", new User());
        return ADMINS_FORM;
    }

    @PostMapping("/new")
    public String saveNewAdmin(@Valid Admin admin, BindingResult binding,ModelMap model) {
        if(binding.hasErrors()) {
            return ADMINS_FORM;
        } else {
            Map<Boolean, List<String>> m = userService.checkUser(admin.getUser());

            if(m.containsKey(false)){
                List<String> ls = m.get(false);
                for (int i = 0; i < ls.size(); i++){
                    model.addAttribute("message", ls);
                }
                return ADMINS_FORM;
            }

            adminService.save(admin);
            model.addAttribute("message", "The admin was created successfully!");
            return getHomeAdmin(model);
        }
    }

    @GetMapping
    public String getHomeAdmin(ModelMap model){
        model.addAttribute("petitions", userService.notEnableAdvice());
        model.addAttribute("admins", adminService.findAll());
        return HOME_ADMIN;
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model){
        model.addAttribute("clientUsers", userService.findByCategory(Categoria.CLIENTE));
        model.addAttribute("employeeUsers", userService.findByCategory(Categoria.EMPLEADO));
        return ADMIN_USERS;
    }

    @GetMapping("/users/{username}/turn_on")
    public String activeUser(@PathVariable("username") String username,ModelMap model){
        Optional<User> u = userService.findUser(username);

        if(u.isPresent()){
            Optional<Cliente> c = clienteService.clientByUsername(username);
            if(c.isPresent()){
                if(c.get().getSuscripcion() == null){
                    model.addAttribute("message", "All client must have a subscription type before enable their users");
                    return getUsers(model);
                }
            }
            if(!u.get().isEnabled()){
                u.get().setEnabled(true);
                model.addAttribute("message", "State of user " + u.get().getUsername() + " has been changed");
                userService.saveUser(u.get());
                log.info(String.format("User with username %s is now enabled", u.get().getUsername()));
            }else{
                model.addAttribute("message","This user is already enable, delete it if you wont use it anymore.");
            }
        }else{
            model.addAttribute("message", "Ups that username doesnt exist, there must be a problem");
        }

        return getUsers(model);
    }

    @GetMapping("/users/{username}/delete")
    public String deleteUserAnsAsociated(@PathVariable("username") String username,ModelMap model){
        Optional<User> u = userService.findUser(username);
        if(u.isPresent()){
            Optional<Cliente> c = clienteService.clientByUsername(username);
            if (c.isPresent()){
                clienteService.delete(c.get());
            }else {
                Optional<Employee> e = employeeService.employeeByUsername(username);
                e.ifPresent(employee -> employeeService.delete(employee));
            }
            model.addAttribute("message", "User deleted succesfully");
        }else{
            model.addAttribute("message","Ups that username doesnt exist, there must be a problem");
        }
        return getUsers(model);
    }

    @ModelAttribute("months")
    public List<Month> getMonths(){
        return Arrays.stream(Month.class.getEnumConstants()).collect(Collectors.toList());
    }

    @GetMapping("/birthdays")
    public String viewBirthdays(ModelMap model) {
        //aquí NO está pasando nada raro, no mirar... ¿no tienes sueño? zzzz, qué sueño...
        Object tempBean = new Object(){
            public String month = "";
            public String getMonth() { return month; }
            public void setMonth(String month) { this.month = month; }
        };
    
        model.addAttribute("tempBean", tempBean);
        return ADMIN_BDAYS;
    }

    @PostMapping("/birthdays")
    public String viewBirthdays(@RequestParam("month") String month, ModelMap model) {
        Object tempBean = new Object(){
            public String month = "";
            public String getMonth() { return month; }
            public void setMonth(String month) { this.month = month; }
        };
    
        model.addAttribute("tempBean", tempBean);
        model.addAttribute("month", month);

        model.addAttribute("clientBdays", clienteService.findClientByBirthdayMonth(Month.valueOf(month)));
        model.addAttribute("employeeBdays", employeeService.findEmployeeByBirthdayMonth(Month.valueOf(month)));
        return ADMIN_BDAYS;
    }


    @GetMapping("/newEmail")
    public String sendNewEmail(ModelMap model){
        model.addAttribute("email", new Email());
        return ADMIN_EMAIL;
    }

    @PostMapping("/newEmail")
    public String sendNewEmail(@Valid Email email, BindingResult binding, ModelMap model){
        if (binding.hasErrors()){
            log.info(String.format("Someone tried to send an email with errors"));
            model.addAttribute("message", "The email couldnt be send");
        }else{
            log.info(String.format("Email sent"));
            model.addAttribute("message", "Email sent succesfully");
            emailService.sendMail(email);
        }
        return getHomeAdmin(model);
    }

    @GetMapping("/newAnnouncement")
    public String sendNewAnnouncement(ModelMap model){
        model.addAttribute("email", new Email());
        return ADMIN_ANNOUNCEMENT;
    }

    @PostMapping("/newAnnouncement")
    public String sendNewAnnouncement(@Valid Email email, BindingResult binding, ModelMap model){
        if (binding.hasErrors()){
            log.info(String.format("Someone tried to send an announcement with errors"));
            model.addAttribute("message", "The announcement couldnt be send");
        }else{
            /*Segun el tipo de subject que haya escogido adjuntara todos los emails de cliente,empleado
            o ambos. Tambien parsearemos a String[] ya que es el formato usado por la API de email*/

            List<String> client_names = clienteService.findAll().stream().map(c -> c.getEmail()).collect(Collectors.toList());
            List<String> employee_names = employeeService.findAll().stream().map(e -> e.getEmail()).collect(Collectors.toList());

            String[] arr_clients = parsetoArray(client_names);
            String[] arr_employee = parsetoArray(employee_names);

            List<String> all_names = new ArrayList<>(client_names);
            all_names.addAll(employee_names);

            String[] all_arr = parsetoArray(all_names);
            log.info(String.format("Announcement sent"));
            model.addAttribute("message", "Email sent succesfully");
            if(email.getAddress()[0].equals("ALL")){
                email.setAddress(all_arr);
            } else if(email.getAddress()[0].equals("ALL_EMPLOYEES")){
                email.setAddress(arr_employee);
            } else if(email.getAddress()[0].equals("ALL_CLIENTS")){
                email.setAddress(arr_clients);
            }

            emailService.sendMail(email);
        }
        return getHomeAdmin(model);
    }

    public String[] parsetoArray(List<String> list) {
        String[] arr = new String[list.size()];
        arr = list.toArray(arr);
        return arr;
    }
}


