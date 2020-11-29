package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class CircuitoServiceTests {
	
	@Autowired
	protected CircuitoService circuitoservice;
	
//	@Test
//	@Transactional
//	void insertCircuito() {
//		List<Sala> salas = new ArrayList<Sala>();
//		List<Circuito> circuitos = new ArrayList<Circuito>();		
//		Sala sala1 = new Sala();
//		sala1.setAforo(15);
//		sala1.setCircuitos(circuitos);
//		sala1.setDescripcion("Soy sala1 jeje");
//		sala1.setId(1);
//		sala1.setName("Piscina");
//		
//		Sala sala2 = new Sala();
//		sala2.setAforo(13);
//		sala2.setCircuitos(circuitos);
//		sala2.setDescripcion("Soy sala2 jeje");
//		sala2.setId(2);
//		sala2.setName("Pesas");
//		
//		salas.add((Sala) sala1);
//		salas.add((Sala) sala2);
//		
//		Circuito circuito = new Circuito();
//		circuito.setAforo(12);
//		circuito.setDescripcion("Hola soy un circuito");
//		circuito.setId(3);
//		circuito.setName("Cardio");
//		circuito.setSalas(salas);
//		circuitoservice.save(circuito);
//	}
	
	@Test
	void mostrarListaConCircuitos() {
		List<Circuito> circuitos = (List<Circuito>) circuitoservice.findAll();
		assertEquals(2, circuitos.size());
	}
	
	@Test
	void mostrarCircuitosPorId() {
		Integer id = 1;
		List<Circuito> circuitos = circuitoservice.findByIdLista(id);
		assertFalse(circuitos.size() == 0);
	}
	
	@Test
	void calcularAforoDelCircuito() {
		Circuito circuito = new Circuito();
		circuito.setAforo(12);
		assertFalse(circuito.getAforo() == 0);
	}
	
	

}
