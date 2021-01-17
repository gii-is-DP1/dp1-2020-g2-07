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
import java.util.HashSet;
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
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
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
	
	@BeforeEach
	private void setup() {
		
		User u = new User();
		Employee e = new Employee();
		Cliente c = new Cliente();
		
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
        
		Sala sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
		
		Horario h = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
		
		Sesion s = new Sesion(new HashSet<Cita>(),LocalTime.of(10, 00),LocalTime.of(12, 00),sala,h,null);
		
		h.addSesion(s);
		
		List<Horario> horarios = new ArrayList<Horario>();
		horarios.add(h);
		
		e.setHorarios(horarios);
		
		given(this.horarioService.findAll()).willReturn(horarios);
		given(this.employeeService.findById(TEST_EMPLOYEE_ID)).willReturn(Optional.of(e));
		given(this.horarioService.dayAlreadyInSchedule(h)).willReturn(false);
		given(this.horarioService.findById(TEST_HORARIO_ID)).willReturn(Optional.of(h));
		given(this.horarioService.findSesionesHorario(TEST_HORARIO_ID)).willReturn(h.getSesiones());
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testAddDaySchedule() throws Exception{
		mockMvc.perform(get("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)).andExpect(status().isOk())
		.andExpect(view().name(HORARIO_FORM))
		.andExpect(model().attributeExists("horario"));
	}
	
	
//	@WithMockUser(value = "spring")
//	@Test
//	public void testAddDaySecheduleSuccess() throws Exception{
//		mockMvc.perform(post("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)
//					.with(csrf())
//					.param("fecha", "2021/3/15"))
//			.andExpect(status().is3xxRedirection())
//			.andExpect(view().name("redirect:/employees/{employeeId}"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testAddDayScheduleHasErrors() throws Exception{
		mockMvc.perform(post("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)
					.with(csrf())
					.param("fecha", "2020/12/25")
					.param("employee", "Fran"))
			.andExpect(model().attributeHasNoErrors("horario"))
			.andExpect(view().name(HORARIO_FORM));
	}
	

}
