package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

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
import org.springframework.samples.petclinic.repository.HorarioRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class HorarioMockedServiceTests {
	
	@Mock
	private HorarioRepository horarioRepo;
	
	private HorarioService horarioService;
	
	private User u;
	private Employee e;
	private Horario h;
	private Sesion s;
	private Sala sala;
	private Cliente c;
	private Optional<Horario> hOptional;
	
	private Collection<Horario> horarios;
	private Collection<Sesion> sesiones;
	
	@Before
	public void setup() {
		horarioService = new HorarioService(horarioRepo);
		
		u = new User();
		e = new Employee();
		c = new Cliente();
		
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
        c.setCitas(new HashSet<Cita>());
        
        h = new Horario();
        h.setFecha(LocalDate.of(2021, 3, 14));
        h.setEmployee(e);
        h.setId(1);
        h.setSesiones(new ArrayList<Sesion>());
        
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
        s.setCitas(new HashSet<Cita>());
        
        sesiones = new HashSet<Sesion>();
        sesiones.add(s);
        
        horarios = new HashSet<Horario>();
        horarios.add(h);
        
        hOptional = Optional.of(h);
        
        when(horarioRepo.findAll()).thenReturn(horarios);
        when(horarioRepo.findById(1)).thenReturn(hOptional);
        when(horarioRepo.save(h)).thenReturn(h);
        when(horarioRepo.getSesionBySala(1)).thenReturn(sesiones);
	}
	
	@Test
	public void shouldFindAll() {
		Collection<Horario> horariosExample = this.horarioService.findAll();
		
		assertThat(horariosExample).hasSize(1);
		assertThat(horariosExample.iterator().next().getFecha()).isEqualTo(LocalDate.of(2021, 3, 14));
	}
	
    @Test
    public void shouldFindHorarioById(){
        Optional<Horario> h1 = horarioService.findById(1);
        assertTrue(h1.isPresent());

        Optional<Horario> h2 = horarioService.findById(2);
        assertFalse(h2.isPresent());
    }
	
    @Test
    public void shouldSave(){
        horarioService.save(h);
    }
    
    @Test
    public void shouldDelete(){
        horarioService.delete(h);
    }
    
    public void shouldAddSession() {
    	/*Añadimos una sesion al horario ya creado*/
    	horarioService.addSesion(1, s);
    	assertThat(h.getSesiones()).hasSize(1);
    	assertThat(h.getSesiones().get(1).getSala().getName()).isEqualTo("Piscina");
    }
    
    @Test
    public void shouldFindSessionsByHorario() {
    	
    }
    
    @Test
    public void shouldfindActiveSessions() {
    	/*Test que devuelve solo las sesiones que superan la fecha de hoy y no han completado su aforo con citas para la sala indicada*/
        Horario h2 = new Horario();
        h2.setFecha(LocalDate.of(2020, 3, 14));
        h2.setEmployee(e);
        h2.setId(2);
        h2.setSesiones(new ArrayList<Sesion>());
        
        Sesion s2 = new Sesion();
        s2.setHoraInicio(LocalTime.of(10, 00));
        s2.setHoraFin(LocalTime.of(12, 00));
        s2.setId(1);
        s2.setHorario(h2);
        s2.setSala(sala);
        s2.setCitas(new HashSet<Cita>());
        sesiones.add(s2);
        
        Collection<Sesion> activas = horarioService.activeSessions(1, c);
        assertThat(activas).hasSize(1); 
        
        sala.setAforo(0);
        activas = horarioService.activeSessions(1, c);
        assertThat(activas).isEmpty();; 
    }
    
    
    

}
