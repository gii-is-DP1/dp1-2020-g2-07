package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.CircuitoService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = CircuitoController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class CircuitoControllerTest {
	
	private static final int TEST_CIRCUITO_ID=1;
	
	@Autowired
	private MockMvc mockMvc;
	private Circuito c;
	private Optional<Circuito> circuitoOpt;
	private Sala s;
	private Sala s2;
	private Collection<Circuito> circuitos;
	
	@MockBean
	private CircuitoService circuitoService;
	
	@MockBean
	private SalaService salaService;
	
	
	
	@BeforeEach
	private void setUp() {
		
		 c = new Circuito();
		 s = new Sala();
		 s2 = new Sala();
		List<Sala> salas= new ArrayList<Sala>();
		
		s.setName("Jacuzzi");
		s.setAforo(7);
		s.setDescripcion("Prueba sobre las salas");
		s.setId(1);
		
		s2.setName("Relax Pool");
		s2.setAforo(7);
		s2.setDescripcion("Prueba sobre las salas");
		s2.setId(2);
		
		salas.add(s);
		salas.add(s2);
		c.setSalas(salas);

		c.setName("Circuito");
		c.setDescripcion("Circuito lleno de relajacion");
		c.setAforo(circuitoService.getAforo(c));
		c.setId(1);
		
		
		circuitos = new ArrayList<Circuito>();
		circuitos.add(c);
		circuitoOpt=Optional.of(c);
		given(this.circuitoService.findAll()).willReturn(circuitos);
		given(this.circuitoService.findById(TEST_CIRCUITO_ID)).willReturn(circuitoOpt);
		given(this.circuitoService.findRoomByCircuit()).willReturn(salas);
		
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testCreationCircuit() throws Exception{
		mockMvc.perform(get("/circuitos/new")).andExpect(status().isOk())
		.andExpect(view().name("circuitos/createOrUpdateCircuitosForm"))
		.andExpect(model().attributeExists("circuito"))
		.andExpect(model().attributeExists("salas"));
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testCreationCircuitSuccess() throws Exception{
		mockMvc.perform(post("/circuitos/new").with(csrf()).param("name", "Circuito")
				.param("salas","Jacuzzi","Relax Pool")
				.param("aforo", "7")
				.param("descripcion", "Circuito lleno de relajacion"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("message","The circuit was created successfully."))
		.andExpect(view().name("circuitos/CircuitosListing"));
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testCreationCircuitHasErrors() throws Exception{
		mockMvc.perform(post("/circuitos/new").with(csrf()).param("name", "Circuito")
				.param("salas","")
		.param("aforo", "siete").param("descripcion", "Circuito lleno de relajacion"))	
		.andExpect(model().attributeHasErrors("circuito"))
		.andExpect(model().attributeHasFieldErrors("circuito", "aforo"))
		.andExpect(model().attributeHasFieldErrors("circuito", "salas"))
		.andExpect(status().isOk())
		.andExpect(view().name("circuitos/createOrUpdateCircuitosForm"));
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testEditCircuit() throws Exception{
		mockMvc.perform(get("/circuitos/{id}/edit",TEST_CIRCUITO_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("circuitos/createOrUpdateCircuitosForm"))
		.andExpect(model().attributeExists("circuito"))
		.andExpect(model().attributeExists("salas"));
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testEditCircuitSuccess() throws Exception{
		mockMvc.perform(post("/circuitos/{id}/edit",TEST_CIRCUITO_ID).with(csrf()).param("name", "Circuito")
				.param("salas","Jacuzzi","Sauna")
				.param("aforo", "6").param("descripcion", "Circuito lleno de relajacion"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("message","The circuit was updated successfully."))
		.andExpect(view().name("circuitos/CircuitosListing"));
		
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testEditCircuitFailure() throws Exception{
		mockMvc.perform(get("/circuitos/{id}/edit",3).with(csrf()))
		.andExpect(status().isOk())
		.andExpect(model().attribute("message","We could not find the circuit you are trying to edit."))
		.andExpect(view().name("circuitos/CircuitosListing"));
		
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testDeleteCircuitSuccess() throws Exception{
		mockMvc.perform(get("/circuitos/{id}/delete",TEST_CIRCUITO_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("circuitos/CircuitosListing"))
		.andExpect(model().attribute("message","The circuit was deleted successfully."));
	}
	
	@WithMockUser(value="spring")
	@Test
	public void testDeleteCircuitFailure() throws Exception{
		mockMvc.perform(get("/circuitos/{id}/delete",2))
		.andExpect(status().isOk())
		.andExpect(view().name("circuitos/CircuitosListing"))
		.andExpect(model().attribute("message","We could not find the circuit you are trying to delete."));
	}
		
}
