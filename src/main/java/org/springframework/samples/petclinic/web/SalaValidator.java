package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Sala;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SalaValidator implements Validator{

	private static final String REQUIRED = "required";
	private static final String CONDITION = "the field must have 10 letters at least";
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Sala.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Sala sala = (Sala) obj;
		String name= sala.getName();
		//name validation
		if (!StringUtils.hasLength(name) || name.length()>50 || name.length()<3) {
			errors.rejectValue("name", REQUIRED+" and between 3 and 50 characters", REQUIRED+" and between 3 and 50 character");
		}
		//description validation
		if(sala.getDescripcion().length()<10) {
			errors.rejectValue("descripcion", CONDITION,CONDITION);
		}
		
		
	}
	
	

}
