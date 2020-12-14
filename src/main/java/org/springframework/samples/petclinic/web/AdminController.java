package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Email;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String HOME_ADMIN = "admin/adminHome";
    public static final String ADMIN_EMAIL = "admin/newEmail";
    public static final String ADMIN_ANNOUNCEMENT = "admin/newAnnouncement";
    public static final String ADMIN_USERS = "admin/checkUsers";

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String getHomeAdmin(ModelMap model){
        model.addAttribute("petitions", userService.notEnableAdvice());
        return HOME_ADMIN;
    }

    @GetMapping("/users")
    public String getUsers(ModelMap model){
        model.addAttribute("users", userService.findAll());
        return ADMIN_USERS;
    }

    @GetMapping("/users/{username}")
    public String activeUser(@PathVariable("username") String username,ModelMap model){
        User u = userService.findUser(username).get();
        if(u.isEnabled()){
            u.setEnabled(false);
        }else{
            u.setEnabled(true);
        }
        model.addAttribute("message", "State of user " + u.getUsername() + " has been changed");
        userService.saveUser(u);
        return getUsers(model);
    }

    @GetMapping("/newEmail")
    public String sendNewEmail(ModelMap model){
        model.addAttribute("email", new Email());
        return ADMIN_EMAIL;
    }

    @PostMapping("/newEmail")
    public String sendNewEmail(@Valid Email email, BindingResult binding, ModelMap model){
        if (binding.hasErrors()){
            model.addAttribute("message", "The email couldnt be send");
        }else{
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
            model.addAttribute("message", "The email couldnt be send");
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

