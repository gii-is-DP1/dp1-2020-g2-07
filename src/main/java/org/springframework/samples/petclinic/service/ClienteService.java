package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import javax.validation.Valid;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clientRepo;
    
    public void save(@Valid Cliente cliente) {
		clientRepo.save(cliente);
    }
    
    public Collection<Cliente> findAll(){
        return clientRepo.findAll();
    }
    
    @Transactional(readOnly = true)
	public Cliente findClienteById(int id) throws DataAccessException {
		return clientRepo.findById(id);
	}
    
}
