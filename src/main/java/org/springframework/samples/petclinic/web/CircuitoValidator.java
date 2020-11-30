package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.swing.plaf.ListUI;

import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;



public class CircuitoValidator implements Validator {
	String IMPOSSIBLE_CIRCUIT="ImpossibleCircuit";

	@Override
	public boolean supports(Class<?> clazz) {
		return Circuito.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Circuito circuito=(Circuito) obj;
		if(circuito.getSalas().size()<2) {
				errors.rejectValue("salas", IMPOSSIBLE_CIRCUIT+"The circuit needs to have 2 or more rooms");
		}

	}

}
