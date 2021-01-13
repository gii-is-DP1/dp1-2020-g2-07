package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.CircuitoRepository;
import org.springframework.samples.petclinic.repository.SalaRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedCircuitoNameException;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CircuitMockTest {
	
	@Mock
	private CircuitoRepository circuitoRepo;
	
	
	@Mock
	private SalaRepository salaRepo;
	
	@Mock 
	private CircuitoService circuitoService;
	private Circuito c;
	private Sala s;
	private Sala s2;
//	private Sala s2;
	private List<Sala> salas;
	private Collection<Circuito> circuitos;
	private Optional<Circuito> circuitoOpt;
	
	@Before
	public void setUp() {
		circuitoService= new CircuitoService(circuitoRepo);
		c = new Circuito();
		s = new Sala();
		s2 = new Sala();
		salas= new ArrayList<Sala>();
		circuitos=new ArrayList<Circuito>();
		
		s.setName("Jacuzzi");
		s.setAforo(7);
		s.setDescripcion("Prueba sobre las salas");
		s.setId(1);
		
		s2.setName("Relax Pool");
		s2.setAforo(7);
		s2.setDescripcion("Prueba sobre las salas");
		s2.setId(2);
		
		salas.add(s);salas.add(s2);
		c.setSalas(salas);

		c.setName("Circuito");
		c.setDescripcion("Circuito lleno de relajacion");
		c.setAforo(circuitoService.getAforo(c));
		c.setId(1);
		
		circuitos.add(c);
		
		circuitoOpt=Optional.of(c);
		
		 when(circuitoRepo.findAll()).thenReturn(circuitos);
		 when(circuitoRepo.findById(1)).thenReturn(circuitoOpt);
		 when(circuitoRepo.save(c)).thenReturn(c);
	
		
	}
	@Test
	public void shouldFindAll() {
		Collection<Circuito> circuitoExample=this.circuitoService.findAll();
		
		assertThat(circuitoExample).hasSize(1);
		assertThat(circuitoExample.iterator().next().getName()).isEqualTo("Circuito");
		
	}
	
	@Test
	public void shouldFindById() {
		Optional<Circuito> optionalCircuitoExample1=circuitoService.findById(1);
		assertTrue(optionalCircuitoExample1.isPresent());
		
		Optional<Circuito> optionalCircuitoExample2=circuitoService.findById(2);
		assertFalse(optionalCircuitoExample2.isPresent());
		
	
	}
	
	@Test
	public void shouldSave() {
		circuitoService.save(c);
		
	}
	
	@Test
	public void shouldDeleteCircuit() {
		circuitoService.delete(c);
	}
	
	@Test
	public void shouldThrowExceptionInsertingCircuitsWithTheSameName() {
		c = new Circuito();
		s = new Sala();
		s2 = new Sala();
		salas= new ArrayList<Sala>();
		circuitos=new ArrayList<Circuito>();
		
		s.setName("Jacuzzi");
		s.setAforo(7);
		s.setDescripcion("Prueba sobre las salas");
		s.setId(1);
		
		s2.setName("Relax Pool");
		s2.setAforo(7);
		s2.setDescripcion("Prueba sobre las salas");
		s2.setId(2);
		
		salas.add(s);salas.add(s2);
		c.setSalas(salas);
		
		c.setId(1);
		c.setName("Circuito");
		c.setDescripcion("Circuito lleno de relajacion");
		c.setAforo(circuitoService.getAforo(c));
		
		try {
			circuitoService.saveCircuito(c);
		} catch (DuplicatedCircuitoNameException e) {
			// The room already exist
			e.printStackTrace();
		}
		Circuito anotherCircuitWithTheSameName= new Circuito();
		anotherCircuitWithTheSameName.setId(2);
		anotherCircuitWithTheSameName.setName("Circuito");
		anotherCircuitWithTheSameName.setAforo(7);
		Assertions.assertThrows(DuplicatedCircuitoNameException.class, () -> {
			circuitoService.saveCircuito(anotherCircuitWithTheSameName);
		});
	}
	
	

}
