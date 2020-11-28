package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Toallas;
import org.springframework.samples.petclinic.repository.ToallasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

@Service
public class ToallasService {

	@Autowired
	ToallasRepository toallasRepo;

	public Collection<Toallas> findAll(){
		return toallasRepo.findAll();
	}

	public Optional<Toallas> findById(int id) {
		return toallasRepo.findById(id);
	}


	public void delete(Toallas toalla) {
		toallasRepo.deleteById(toalla.getId());

	}

	@Transactional(readOnly = true)	
	public Collection<Toallas> findToallas() throws DataAccessException {
		return toallasRepo.findAll();
	}	
}