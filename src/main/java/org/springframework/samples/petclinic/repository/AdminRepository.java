package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Admin;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
    Optional<Admin> findById(Integer id);

    void deleteById(Integer id);
}
