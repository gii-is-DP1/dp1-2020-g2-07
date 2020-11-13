package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Toallas;
import org.springframework.samples.petclinic.repository.ToallasRepository;
import org.springframework.stereotype.Service;

@Service
public class ToallasService {

	@Autowired
	ToallasRepository toallasRepo;

	public Collection<Toallas> findAll(){
		return toallasRepo.findAll();
	}
}