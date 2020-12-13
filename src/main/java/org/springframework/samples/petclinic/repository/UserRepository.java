package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.User;

import java.util.Collection;


public interface UserRepository extends  CrudRepository<User, String>{
	Collection<User> findAll();
}
