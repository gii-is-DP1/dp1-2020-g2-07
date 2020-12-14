package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class BonoServiceTests {
	@Autowired
	protected BonoService bonoservice;
	
	@Test
	@Transactional
	void insertBono() {
		Bono bono = new Bono();
		bono.setCodigo();
		bono.setDescripcion("Soy una descripción");
		//bono.setDuracion(LocalDate.of(2021, 02, 01));
		bono.setId(1);
		bono.setPrecio(2);
		bono.setUsado(true);
		bonoservice.save(bono);
	}
	
	@Test
	void mostrarListaConBonos() {
		List<Bono> bonos = (List<Bono>) bonoservice.findAll();
		assertEquals(2, bonos.size());
	}
	
	@Test
	void mostrarBonosPorId() {
		Integer id = 1;
		List<Bono> bonos = bonoservice.findByIdLista(id);
		assertFalse(bonos.size() == 0);
	}
	
}
