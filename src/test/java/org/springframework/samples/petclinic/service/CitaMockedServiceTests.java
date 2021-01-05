package org.springframework.samples.petclinic.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CitaRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CitaMockedServiceTests {
	
	@Mock
	private CitaRepository citaRepo;
	
	private CitaService citaService;
	
	private Cita cita;
	private Cliente c;
	private User u;
	private Sesion s;
	private Horario h;
	private Employee e;
	private Sala sala;
	
	private Collection<Cita> citas;
	
	@Before
	public void setup() {
		citaService=new CitaService(citaRepo);
		c = new Cliente();
		u = new User();
		
        u.setUsername("juanma");
        u.setPassword("12345");
        u.setEnabled(true);
        c.setFirst_name("Juanma");
        c.setLast_name("Garcia");
        c.setCategory(Categoria.CLIENTE);
        c.setSuscripcion(SubType.AFTERNOON);
        c.setId(1);
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        
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
        
        h = new Horario();
        h.setFecha(LocalDate.of(2021, 3, 14));
        h.setEmployee(e);
        h.setId(1);
        
		sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
        
        s = new Sesion();
        s.setHoraInicio(LocalTime.of(10, 00));
        s.setHoraFin(LocalTime.of(12, 00));
        s.setId(1);
        s.setHorario(h);
        s.setSala(sala);
		
        cita = new Cita();
		cita.setCliente(c);
		cita.setSesion(s);
		cita.setId(1);
        
		citas = new HashSet<Cita>();
		citas.add(cita);
		
		when(citaRepo.findAll()).thenReturn(citas);
	}
	
	@Test
	public void shouldFind() {
		
		Collection<Cita> citaExample = this.citaService.findAll();
		assertThat(citaExample).hasSize(1);
		assertThat(citas.iterator().next().getCliente().getFirst_name()).isEqualTo("Juanma");
		
	}

}
