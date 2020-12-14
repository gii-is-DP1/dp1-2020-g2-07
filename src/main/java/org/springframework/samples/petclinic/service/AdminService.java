package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.repository.AdminRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;


    public Optional<Admin> findById(int id){
        return adminRepository.findById(id);
    }

    public void delete(Admin admin) {
        adminRepository.deleteById(admin.getId());
    }

    public void save(@Valid Admin admin){
        adminRepository.save(admin);

        userService.saveUser(admin.getUser());
        //creating authorities
        authoritiesService.saveAuthorities(admin.getUser().getUsername(), "admin");

    }
}
