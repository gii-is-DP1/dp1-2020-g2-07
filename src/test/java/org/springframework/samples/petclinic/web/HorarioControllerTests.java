package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.model.RoomType;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HorarioController.class, 
excludeFilters = @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class HorarioControllerTests {
	
	private static final int TEST_EMPLOYEE_ID = 1;
	private static final int TEST_HORARIO_ID = 1;
	public static final String HORARIO_FORM ="schedule/horarioForm";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HorarioService horarioService;
	
	@MockBean
	private SalaService salaService;
	
	@MockBean
	private EmployeeService employeeService;
	
	private Employee e;
	private User u;
	private Sala sala;
	private Horario h;
	private Sesion s;
	
	private List<Horario> horarios;
	private Collection<Sala> salas;
	
	@BeforeEach
	private void setup() {
		
		u = new User();
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
        
		sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
		sala.setSesiones(new ArrayList<Sesion>());
		sala.setRoom_type(RoomType.LIFE_GUARD);
		
		h = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
		
		s = new Sesion();
		s.setId(1);
		s.setHoraInicio(LocalTime.of(10, 00));
		s.setHoraFin(LocalTime.of(12, 00));
		s.setHorario(h);
		s.setSala(sala);
		s.setCitas(new HashSet<Cita>());
		
		h.addSesion(s);
		
		sala.getSesiones().add(s);
		
		horarios = new ArrayList<Horario>();
		horarios.add(h);
		
		salas = new ArrayList<Sala>();
		salas.add(sala);
		
		e.setHorarios(horarios);
		
		given(this.horarioService.findAll()).willReturn(horarios);
		given(this.horarioService.findById(TEST_HORARIO_ID)).willReturn(Optional.of(h));
		given(this.horarioService.findSesionesHorario(TEST_HORARIO_ID)).willReturn(h.getSesiones());
		given(this.horarioService.calcDays(TEST_EMPLOYEE_ID, "past")).willReturn(new ArrayList<Horario>());
		given(this.horarioService.SesionHours(LocalTime.parse("09:00"))).willReturn(Lists.newArrayList(LocalTime.of(9,00),LocalTime.of(10,00),LocalTime.of(11,00),LocalTime.of(12,00)));
		given(this.horarioService.SesionHours(LocalTime.parse("10:00"))).willReturn(Lists.newArrayList(LocalTime.of(10,00),LocalTime.of(11,00),LocalTime.of(12,00),LocalTime.of(13,00)));
		given(this.horarioService.checkDuplicatedSessions(s)).willReturn(false);
		
		given(this.employeeService.findById(TEST_EMPLOYEE_ID)).willReturn(Optional.of(e));
		given(this.salaService.findAll()).willReturn(salas);
		
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testAddDaySchedule() throws Exception{
		mockMvc.perform(get("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)).andExpect(status().isOk())
		.andExpect(view().name(HORARIO_FORM))
		.andExpect(model().attributeExists("horario"))
		.andExpect(model().attributeExists("employee"));
	}
	
	
	@WithMockUser(value = "admin")
	@Test
	public void testAddDaySecheduleSuccess() throws Exception{
		/*Comprobar que se puede añadir un nuevo día al horario*/
		given(this.horarioService.dayAlreadyInSchedule(h)).willReturn(false);
		mockMvc.perform(post("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)
					.with(csrf())
					.param("fecha", "2021/03/15")
					.param("employee.id", "1"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/employees/1"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testAddDayScheduleHasErrorsDayInThePast() throws Exception{
		/*Comprobamos que al añadir un día al horario este no pueda estar en el pasado*/
		mockMvc.perform(post("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)
					.with(csrf())
					.param("fecha", "2020/12/25")
					.param("employee.id", "1"))
			.andExpect(model().attribute("message","The day you picked can't be in the past"))
			.andExpect(view().name(HORARIO_FORM));
	}	
	
	@WithMockUser(value = "admin")
	@Test
	public void testShowSesiones() throws Exception{
		/*Comprobamos las sesiones que tiene un empleado para cierto día*/
		mockMvc.perform(get("/employees/{employeeId}/schedule/{horarioId}", TEST_EMPLOYEE_ID, TEST_HORARIO_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("horario"))
			.andExpect(model().attributeExists("sesion"))
			.andExpect(view().name("employees/employeeSchedule"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testShowSesionesInThePast() throws Exception{
		/*Comprobamos las sesiones que tiene un empleado para la fecha anterior a hoy*/
		mockMvc.perform(get("/employees/{employeeId}/pastSessions", TEST_EMPLOYEE_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("past"))
			.andExpect(view().name("employees/employeePastSessions"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testGetNewSesion() throws Exception{
		/*Comprobamos las sesiones que tiene un empleado para cierto día*/
		mockMvc.perform(get("/employees/{employeeId}/schedule/{horarioId}/newSesion", TEST_EMPLOYEE_ID, TEST_HORARIO_ID))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("newSesion"))
			.andExpect(model().attributeExists("sesion"))
			.andExpect(view().name("schedule/sesionForm"));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void testNewSesionPost() throws Exception{
		/*Comprobamos las sesiones que tiene un empleado para cierto día*/
		mockMvc.perform(post("/employees/{employeeId}/schedule/{horarioId}/newSesion", TEST_EMPLOYEE_ID, TEST_HORARIO_ID)
				.with(csrf())
				.param("horaInicio", "12:00")
				.param("horaFin", "13:00")
				.param("horario","1")
				.param("sala","Piscina"))
//			.andExpect(model().attribute("message", "The employee is not qualified to work in this room"))
//			.andExpect(model().attributeHasFieldErrors("newSesion","horaFin"))
//			.andExpect(view().name("schedule/sesionForm"));
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/employees/1"));
	}
	
	

}
