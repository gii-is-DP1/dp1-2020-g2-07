package org.springframework.samples.petclinic.service;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.repository.SalaRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class RoomMockTest {
	
	@Mock
	private SalaRepository salasRepo;
	
	@Mock
	private SalaService salaService;
	private Sala s;
	private List<Circuito> circuitos;
	private Circuito c1;
	private Circuito c2;
	private Collection<Sala> salas;
	private Optional<Sala> salaOpt;
	
	@Before
	public void setUp() {
		salaService= new SalaService(salasRepo);
		s= new Sala();
		c1= new Circuito();
		c2= new Circuito();
		circuitos= new ArrayList<Circuito>();
		salas= new ArrayList<Sala>();
		
		c1.setAforo(7);
		c1.setDescripcion("Prueba de circuito");
		c1.setId(1);
		c1.setName("Circuito1");
		
		c2.setAforo(7);
		c2.setDescripcion("Prueba de circuito");
		c2.setId(1);
		c2.setName("Circuito2");
	
		
		circuitos.add(c1);circuitos.add(c2);
		s.setCircuitos(circuitos);
		
		s.setId(1);
		s.setName("Jacuzzi");
		s.setAforo(7);
		
		salas.add(s);
		
		salaOpt=Optional.of(s);
		
		when(salasRepo.findAll()).thenReturn(salas);
		when(salasRepo.findById(1)).thenReturn(salaOpt);
		when(salasRepo.save(s)).thenReturn(s);
	
	}
	
	@Test
	public void shouldFindAll() {
		Collection<Sala> salaExample=this.salaService.findAll();
		
		assertThat(salaExample).hasSize(1);
		assertThat(salaExample.iterator().next().getName()).isEqualTo("Jacuzzi");
		
	}
	
	@Test
	public void shouldFindById() {
		Optional<Sala> optionalSalaExample1=salaService.findById(1);
		assertTrue(optionalSalaExample1.isPresent());
		
		Optional<Sala> optionalSalaExample2=salaService.findById(2);
		assertFalse(optionalSalaExample2.isPresent());
		
	
	}
	
	@Test
	public void shouldSave() {
		salaService.save(s);
		
	}
	
	@Test
	public void shouldDeleteRoom() {
		salaService.delete(s);
	}
	
	@Test
	public void shouldThrowExceptionInsertingRoomsWithTheSameName() {
		s= new Sala();
		s.setId(1);
		s.setName("Jacuzzi");
		s.setAforo(7);
		try {
			salaService.saveSala(s);
		} catch (DuplicatedSalaNameException e) {
			// The room already exist
			e.printStackTrace();
		}
		Sala anotherRoomWithTheSameName= new Sala();
		anotherRoomWithTheSameName.setId(2);
		anotherRoomWithTheSameName.setName("Jacuzzi");
		anotherRoomWithTheSameName.setAforo(7);
		Assertions.assertThrows(DuplicatedSalaNameException.class, () -> {
			salaService.saveSala(anotherRoomWithTheSameName);
		});
	}

}
