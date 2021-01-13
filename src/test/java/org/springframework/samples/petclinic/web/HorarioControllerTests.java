package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
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

import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;

@WebMvcTest(controllers = HorarioController.class, 
excludeFilters = @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class HorarioControllerTests {
	
	private static final int TEST_EMPLOYEE_ID = 1;
	
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
		
		given(this.horarioService.findAll()).willReturn(horarios);
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	public void testShowSchedule() throws Exception{
		mockMvc.perform(get("/employees/{employeeId}/newSchedule", TEST_EMPLOYEE_ID)).andExpect(status().isOk())
		.andExpect(view().name("schedule/horarioForm"))
		.andExpect(model().attributeExists("horario"));
	}

}
