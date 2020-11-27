package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class SalaServiceTest {

	@Autowired
	protected SalaService salaservice;
	
	@Test
	@Transactional
	void insertarSala() {
		Sala sala = new Sala();
		List<Circuito> circuitos = new ArrayList<Circuito>();
		sala.setAforo(12);
		sala.setCircuitos(circuitos);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
		salaservice.save(sala);
	}
	
	@Test
	void mostrarListaConSalas() {
		Collection<Sala> salas = salaservice.findAll();
		assertEquals(4, salas.size());
	}
	
	@Test
	void mostrarSalasPorId() {
		Integer id = 1;
		List<Sala> salas = salaservice.findByIdLista(id);
		assertFalse(salas.size() == 0);
	}
	
}
