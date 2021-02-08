package org.springframework.samples.petclinic.web;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.model.RoomType;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.model.User;
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
	private static final int TEST_CLIENT_ID = 1;
	
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
	
	@MockBean
	private SesionesFormatter sesionesFormatter;
	
	private Horario h;
	private Sesion s;
	private Sala sala;
	private Cliente c;
	private Optional<Cliente> optClient;
	private User u;
	private Employee e;
	private Bono bono;
	
	private Collection<Sala> salas;
	private List<Horario> horarios;
	private Collection<Bono> bonos;
	
	@BeforeEach
	public void setup() throws ParseException {
		salas = new ArrayList<Sala>();
		sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
		sala.setSesiones(new ArrayList<Sesion>());
		sala.setRoom_type(RoomType.LIFE_GUARD);
		salas.add(sala);
		
		
        Authorities a = new Authorities();
        Set<Authorities> aSet = new HashSet<Authorities>();
        a.setAuthority("client");
        aSet.add(a);
		
        c = new Cliente();
        u = new User("juanma","12345",true, aSet);
        c.setFirst_name("Juanma");
        c.setLast_name("Garcia");
        c.setCategory(Categoria.CLIENTE);
        c.setSuscripcion(SubType.AFTERNOON);
        c.setId(TEST_CLIENT_ID);
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        c.setCitas(new HashSet<Cita>());
        optClient = Optional.of(c);
        
		e = new Employee();
		
        u.setUsername("Fran");
        u.setPassword("12345");
        u.setEnabled(true);
        e.setFirst_name("Fran");
        e.setLast_name("Garcia");
        e.setCategory(Categoria.EMPLEADO);
        e.setId(1);
        e.setAddress("C/Pantomima");
        e.setEmail("jmgc@hotmail.com");
        e.setIBAN("ES4131905864163572187270");
        e.setUser(u);
        e.setProfession(Profession.LIFE_GUARD);
		
		h = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
		
		s = new Sesion(new HashSet<Cita>(),LocalTime.of(10, 00),LocalTime.of(12, 00),sala,h);
		
		bono = new Bono("Prueba", 10, LocalDate.parse("2009-12-01"), LocalDate.parse("2100-12-03"), "Texto", false, null);

		h.addSesion(s);
		sala.getSesiones().add(s);
		
		horarios = new ArrayList<Horario>();
		horarios.add(h);
		e.setHorarios(horarios);
		
		bono.setSession(s);
		
		bonos = new ArrayList<Bono>();
		bonos.add(bono);
		
		given(salaService.findAll()).willReturn(salas);
		given(salaService.findById(ID)).willReturn(Optional.of(sala));
		
		given(this.horarioService.findAll()).willReturn(horarios);
		given(this.horarioService.activeSessions(ID, c)).willReturn(h.getSesiones());
		given(this.horarioService.activeSessions(ID, null)).willReturn(h.getSesiones());
		given(this.horarioService.inTimeSessions(h.getSesiones(), c)).willReturn(h.getSesiones());
		
		given(this.sesionesFormatter.print(s, Locale.getDefault())).willReturn(h.getFecha() + ": From " + s.getHoraInicio() + " to " + s.getHoraFin());
		given(this.sesionesFormatter.parse(h.getFecha() + ": From " + s.getHoraInicio() + " to " + s.getHoraFin(), Locale.getDefault())).willReturn(s);
		
		given(this.bonoService.findAll()).willReturn(bonos);
		
		given(this.clienteService.clientByUsername("juanma")).willReturn(optClient);
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testSalasListing() throws Exception{
		/*Muestra el listado de todas las salas*/
		mockMvc.perform(get("/salas")).andExpect(status().isOk())
			.andExpect(model().attributeExists("salas"))
			.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testEditSala() throws Exception{
		/*Get del edit de sala como administrador*/
		mockMvc.perform(get("/salas/{id}/edit", ID)).andExpect(status().isOk())
			.andExpect(model().attributeExists("sala"))
			.andExpect(view().name(SALAS_FORM));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testEditSalaSuccess() throws Exception{
		/*Sala editada con éxito*/
		mockMvc.perform(post("/salas/{id}/edit", ID)
					.with(csrf())
					.param("name", "Piscina")
					.param("aforo", "12")
					.param("descripcion", "Soy una sala"))
			.andExpect(status().isOk())
			.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testEditSalaErrors() throws Exception{
		/*Fallo al editar la sala debido al tamaño del nombre*/
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

	@WithMockUser(value = "admin")
	@Test
	public void testDeleteSala() throws Exception{
		/*Sala eliminada con éxito*/
		mockMvc.perform(get("/salas/{id}/delete",ID)).andExpect(status().isOk())
				.andExpect(view().name(SALAS_LISTING));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testNewSala() throws Exception{
		/*Get del formulario de nueva sala como administrador*/
		mockMvc.perform(get("/salas/new")).andExpect(status().isOk())
			.andExpect(model().attributeExists("sala"))
			.andExpect(view().name(SALAS_FORM));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testNewSalaSuccess() throws Exception{
		/*Sala creada con éxito*/
		mockMvc.perform(post("/salas/new").with(csrf())
				.param("name", "Jacuzzi")
				.param("aforo", "6")
				.param("descripcion", "soy un jacuzzi"))
		.andExpect(status().isOk())
		.andExpect(view().name(SALAS_LISTING));		
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testNewSalaErrors() throws Exception{
		/*Fallo al crear la sala debido al aforo*/
		mockMvc.perform(post("/salas/new").with(csrf())
				.param("name", "Jacuzzi")
				.param("aforo", "0")
				.param("descripcion", "soy un jacuzzi"))
		.andExpect(model().attributeHasErrors("sala"))
		.andExpect(model().attributeHasFieldErrors("sala", "aforo"))
		.andExpect(status().isOk())
		.andExpect(view().name(SALAS_FORM));		
	}
	
	@WithMockUser(value = "juanma")
	@Test
	public void testShowSalaCita() throws Exception{
		/*Sala mostrada de forma individual como cliente, por lo que se incluyen las citas*/
		mockMvc.perform(get("/salas/{id}", ID))
			.andExpect(model().attributeExists("sesion"))
			.andExpect(model().attributeExists("cita"))
			.andExpect(status().isOk())
			.andExpect(view().name("salas/salaDetails"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testShowSalaCitaAdmin() throws Exception{
		/*Sala mostrada de forma individual al administrador, por lo que no se incluyen las citas*/
		mockMvc.perform(get("/salas/{id}", ID))
			.andExpect(model().attributeDoesNotExist("cita","sesion"))
			.andExpect(status().isOk())
			.andExpect(view().name("salas/salaDetails"));
	}
		
	@WithMockUser(value = "juanma")
	@Test
	public void testShowSalaCitaPost() throws Exception{
		/*Creación de una cita para el cliente*/
		mockMvc.perform(post("/salas/{id}", ID)
				.with(csrf())
				.flashAttr("sesion", s)
				.param("cliente","1"))
			.andExpect(status().isOk())
			.andExpect(view().name(SALAS_LISTING));
	}
	
	
	@WithMockUser(value = "admin")
	@Test
	public void testCreateTokenGet() throws Exception{
		/*Get de creación de bono*/
		mockMvc.perform(get("/salas/{id}/createtoken", ID))
			.andExpect(model().attributeExists("bono"))
			.andExpect(status().isOk())
			.andExpect(view().name("bonos/createToken"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testCreateTokenPost() throws Exception{
		/*Creación de un bono con precio 0*/
		mockMvc.perform(post("/salas/{id}/createtoken", ID)
				.with(csrf())
				.param("codigo", "Test")
				.param("precio", "0")
				.param("descripcion", "Texto")
				.param("session", h.getFecha() + ": From " + s.getHoraInicio() + " to " + s.getHoraFin()))
			.andExpect(model().attributeHasErrors("bono"))
			.andExpect(status().isOk())
			.andExpect(view().name("bonos/createToken"));
	}
	
}
