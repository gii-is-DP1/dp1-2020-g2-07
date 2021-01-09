package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.repository.AdminRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdminService {
    AdminRepository adminRepository;

    private UserService userService;

    private AuthoritiesService authoritiesService;

    @Autowired
    public AdminService(AdminRepository adminRepo, UserService userService, AuthoritiesService authoritiesService){
        this.adminRepository = adminRepo;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }


    public Optional<Admin> findById(int id){
        return adminRepository.findById(id);
    }

    public void delete(Admin admin) {
        adminRepository.deleteById(admin.getId());
        userService.delete(admin.getUser());
    }

    public Collection<Admin> findAll(){
        return adminRepository.findAll();
    }

    public void save(@Valid Admin admin){
        adminRepository.save(admin);

        userService.saveUser(admin.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(admin.getUser().getUsername(), "admin");

    }
}
