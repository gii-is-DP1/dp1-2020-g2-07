package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String HOME_ADMIN = "admin/adminHome";

    @Autowired
    ClienteService clienteService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AdminService adminService;

    @GetMapping
    public String getHomeAdmin(){
        return HOME_ADMIN;
    }
}
