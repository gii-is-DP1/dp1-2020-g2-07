package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SalaController.class, 
excludeFilters = @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class RoomControllerTests {
	
	public static final String SALAS_LISTING ="/salas/SalasListing";
	public static final String SALAS_FORM ="/salas/CreateOrUpdateSalasForm";
	public static final int ID = 1;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private SalaService salaService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private BonoService bonoService;
	
	@MockBean
	private CitaService citaService;
	
	@BeforeEach
	public void setup() {
		Collection<Sala> salas = new ArrayList<Sala>();
		Sala sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
		salas.add(sala);
		
		given(salaService.findAll()).willReturn(salas);
		given(salaService.findById(ID)).willReturn(Optional.of(sala));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testSalasListing() throws Exception{
		mockMvc.perform(get("/salas")).andExpect(status().isOk())
			.andExpect(model().attributeExists("salas"))
			.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testEditSalaListing() throws Exception{
		mockMvc.perform(get("/salas/{id}/edit", ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("sala"))
			.andExpect(view().name(SALAS_FORM));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testEditSalaSuccess() throws Exception{
		mockMvc.perform(post("/salas/{id}/edit", ID)
					.with(csrf())
					.param("name", "Piscina")
					.param("aforo", "12")
					.param("descripcion", "Soy una sala"))
			.andExpect(status().isOk())
			.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testEditSalaErrors() throws Exception{
		mockMvc.perform(post("/salas/{id}/edit", ID)
					.with(csrf())
					.param("name", "Pi")
					.param("aforo", "12")
					.param("descripcion", "Soy una sala"))
			.andExpect(model().attributeHasErrors("sala"))
			.andExpect(model().attributeHasFieldErrors("sala", "name"))
			.andExpect(status().isOk())
			.andExpect(view().name(SALAS_FORM));
	}

	@WithMockUser(value = "spring")
	@Test
	public void testDeleteSala() throws Exception{
		mockMvc.perform(get("/salas/{id}/delete",ID)).andExpect(status().isOk())
				.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testNewSala() throws Exception{
		mockMvc.perform(get("/salas/new")).andExpect(status().isOk())
			.andExpect(model().attributeExists("sala"))
			.andExpect(view().name(SALAS_FORM));
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testNewSalaSuccess() throws Exception{
		mockMvc.perform(post("/salas/new").with(csrf())
				.param("name", "Jacuzzi")
				.param("aforo", "6")
				.param("descripcion", "soy un jacuzzi"))
		.andExpect(status().isOk())
		.andExpect(view().name(SALAS_LISTING));		
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testNewSalaErrors() throws Exception{
		mockMvc.perform(post("/salas/new").with(csrf())
				.param("name", "Jacuzzi")
				.param("aforo", "0")
				.param("descripcion", "soy un jacuzzi"))
		.andExpect(model().attributeHasErrors("sala"))
		.andExpect(model().attributeHasFieldErrors("sala", "aforo"))
		.andExpect(status().isOk())
		.andExpect(view().name(SALAS_FORM));		
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testShowSalaCita() throws Exception{
		mockMvc.perform(get("/salas/{id}", ID))
			.andExpect(status().isOk())
			.andExpect(view().name("salas/salaDetails"));
	}
	
	
//	FALTAN EL GET Y EL POST DE LA CITA Y VER SI SE PODRIAN TESTEAR LAS EXCEPCIONES
	
	
}
