package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
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
