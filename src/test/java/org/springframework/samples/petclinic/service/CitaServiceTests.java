package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class CitaServiceTests {
	
	@Autowired
	protected CitaService citaservice;
	
	@Test
	void mostrarListaConCitas() {
		Collection<Cita> citas =  citaservice.findAll();
		assertEquals(0, citas.size());
	}


}