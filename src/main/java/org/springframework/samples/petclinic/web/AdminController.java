package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AdminService;
import org.springframework.samples.petclinic.service.EmailService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    public static final String HOME_ADMIN = "admin/adminHome";

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @GetMapping
    public String getHomeAdmin(ModelMap model){
        model.addAttribute("petitions", userService.notEnableAdvice());
        return HOME_ADMIN;
    }

    @GetMapping("/newEmail")
    public String sendNewEmail(){

        return HOME_ADMIN;
    }
}
