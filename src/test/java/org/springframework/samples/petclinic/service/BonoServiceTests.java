package org.springframework.samples.petclinic.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.junit.Assert.*;

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
		bono.setId(1);
		bono.setPrecio(2);
		bono.setUsado(true);
		bonoservice.save(bono);
	}

	@Test
	void mostrarListaConBonos() {
		List<Bono> bonos = (List<Bono>) bonoservice.findAll();
		assertEquals(1, bonos.size());
	}

}
