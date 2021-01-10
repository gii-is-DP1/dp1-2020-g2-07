/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;

    private ClienteService clienteService;

    private EmployeeService employeeService;

	@Autowired
	public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
	}

	public UserService(UserRepository userRepository, ClienteService clienteService, EmployeeService employeeService) {
        this.userRepository = userRepository;
        this.clienteService = clienteService;
        this.employeeService = employeeService;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		userRepository.save(user);
	}

    public Integer notEnableAdvice(){
	   Collection<User> users = (Collection<User>) userRepository.findAll();
	   Integer numberNotEnable = Math.toIntExact(users.stream().filter(u -> !u.isEnabled()).count());
	    return  numberNotEnable;
    }

    public Collection<User> findAll(){
        return userRepository.findAll();
    }

    public Collection<User> findByCategory(Categoria cat){
        if(cat.equals(Categoria.EMPLEADO)){
            return this.findAll().stream().filter(u -> employeeService.employeeByUsername(u.getUsername()).isPresent()).collect(Collectors.toList());
        }

        if(cat.equals(Categoria.CLIENTE)){
            return this.findAll().stream().filter(u -> clienteService.clientByUsername(u.getUsername()).isPresent()).collect(Collectors.toList());
        }

        return this.findAll()
            .stream()
            .filter(u -> !clienteService.clientByUsername(u.getUsername()).isPresent() || !employeeService.employeeByUsername(u.getUsername()).isPresent())
            .collect(Collectors.toList());

    }

    public void delete(User u){
	    userRepository.delete(u);
    }

	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}

	public Map<Boolean, List<String>> checkUser(User u){
	    Map<Boolean, List<String>> m = new HashMap<Boolean, List<String>>();
	    List<String> errorList = new ArrayList<String>();
	    Boolean expecialCha = false;
        Boolean allRight = true;

        String username = u.getUsername();
	    String password = u.getPassword();

        char[] chars = username.toCharArray();
        for(char c : chars){
            if(!Character.isLetter(c)){
                expecialCha = true;
            }
        }

        if (clienteService.findAll().stream().anyMatch(c -> c.getUser().getUsername().equals(username))){
            errorList.add("Username already chosen");
            allRight = false;
        }

	    if (username.trim().equals("") || username == null){
            errorList.add("Username cant be empty or null");
            allRight = false;
        }

	    if (expecialCha){
            errorList.add("Username cant have any digit or special character");
            allRight = false;
        }

	    if (password.trim().equals("") || password == null){
            errorList.add("Password cant be empty or null");
            allRight = false;
        }

	    m.put(allRight, errorList);
	    return m;
    }
}
