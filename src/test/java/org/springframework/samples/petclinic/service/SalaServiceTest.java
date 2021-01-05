package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
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
		assertEquals(5, salas.size());
	}
	
	@Test
	void mostrarSalasPorId() {
		Integer id = 1;
		List<Sala> salas = salaservice.findByIdLista(id);
		assertFalse(salas.size() == 0);
	}
	
}
