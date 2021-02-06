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
        
        h = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
        
		sala = new Sala();
		sala.setAforo(12);
		sala.setDescripcion("Soy una sala");
		sala.setId(1);
		sala.setName("Piscina");
        
        s = new Sesion(new HashSet<Cita>(),LocalTime.of(10, 00),LocalTime.of(12, 00),sala,h);
        
        sesiones = new HashSet<Sesion>();
        sesiones.add(s);
        
        horarios = new HashSet<Horario>();
        horarios.add(h);
        
        h.addSesion(s);
        
        hOptional = Optional.of(h);
        
        when(horarioRepo.findAll()).thenReturn(horarios);
        when(horarioRepo.findById(1)).thenReturn(hOptional);
        when(horarioRepo.save(h)).thenReturn(h);
        when(horarioRepo.getSesionBySala(1)).thenReturn(sesiones);
        when(horarioRepo.getSesionByHorario(1)).thenReturn(h.getSesiones());
        when(horarioRepo.getHorariosByEmployee(1)).thenReturn(horarios);
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
    
    @Test
    public void shouldAddSession() {
    	/*Añadimos una sesion al horario ya creado*/
    	Sesion s2 = new Sesion(new HashSet<Cita>(),LocalTime.of(12, 00),LocalTime.of(14, 00),sala,h);
    	horarioService.addSesion(1, s2);
    	assertThat(horarioService.findSesionesHorario(1)).hasSize(2);
    	assertTrue(horarioService.findSesionesHorario(1).contains(s2));
    }
    
    @Test
    public void shouldFindSessionsByHorario() {
    	/*Dado un horarioId (día) devuelve todas las sesiones que contiene*/
    	Collection<Sesion> sh = horarioService.findSesionesHorario(1);
    	assertThat(sh).hasSize(1);
    	assertThat(sh.iterator().next().getSala().getName()).isEqualTo("Piscina");
    }
    
    @Test
    public void shouldfindActiveSessions() {
    	/*Test que devuelve solo las sesiones que superan la fecha de hoy y no han completado su aforo con citas para la sala indicada*/
        Horario h2 = new Horario(LocalDate.of(2020, 3, 14),e,new ArrayList<Sesion>());
        
        Sesion s2 = new Sesion(new HashSet<Cita>(),LocalTime.of(10, 00),LocalTime.of(12, 00),sala,h2);       
        sesiones.add(s2);
        
        /*Obtener la sesión que se realizará en el futuro*/        
        Collection<Sesion> activas = horarioService.activeSessions(1, c);
        assertThat(activas).hasSize(1); 
        
        /*No se devolverá ninguna sesión ya que el aforo de la sala es igual al tamaño de las citas
         * Es solo un supuesto, porque el aforo no puede ser 0*/        
        sala.setAforo(0);
        activas = horarioService.activeSessions(1, c);
        assertThat(activas).isEmpty(); 
    }
    
    @Test
    public void shouldFindInTimeSessions() {
    	/*El cliente tiene suscripción de tarde, por lo que no aparecerá la sesión creada al ser de mañana*/
    	
    	Collection<Sesion> inTime = horarioService.inTimeSessions(sesiones, c);
    	assertThat(inTime).isEmpty(); 
    	
        Sesion s2 = new Sesion(new HashSet<Cita>(),LocalTime.of(16, 00),LocalTime.of(17, 00),sala,h);
        sesiones.add(s2);
        
        /*Si añadimos una nueva sesión de tarde, esta si se mostrará en las sesiones disponibles*/        
        inTime = horarioService.inTimeSessions(sesiones, c);
        assertThat(inTime).hasSize(1); 
        assertThat(inTime.iterator().next().getHoraInicio()).isEqualTo(LocalTime.of(16, 00));
    }
    
    @Test
    public void shouldCalcDays() {
    	/*Se comprueba que la función sólo devuelve los días que se le pasan como parámetro y lo hacen de manera ordenada según la fecha*/
    	Horario h2 = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
    	horarios.add(h2);
    	Collection<Horario> future = horarioService.calcDays(1, "future");
    	assertThat(future).hasSize(2);
    	assertThat(future.iterator().next()).isEqualTo(h2);
    	
    	Collection<Horario> past = horarioService.calcDays(1, "past");
    	assertThat(past).isEmpty();
    }
    
    @Test
    public void shouldCheckDuplicatedSessions() {
    	/*Este test comprueba si una nueva sesion esta duplicada cuando se va a insertar*/
    	Sesion s2 = new Sesion(new HashSet<Cita>(),LocalTime.of(11, 00),LocalTime.of(13, 00),sala,h);
    	assertTrue(horarioService.checkDuplicatedSessions(s2));
    	
    	s2.setHoraInicio(LocalTime.of(12,00));
    	assertFalse(horarioService.checkDuplicatedSessions(s2));	
    }
    
    @Test
    public void shouldCheckDayAlreadyInSchedule() {
    	/*Comprueba que no se haya añadido ya ese día al listado de horarios*/
    	Horario h2 = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());    	    	
    	assertTrue(horarioService.dayAlreadyInSchedule(h2));
    	
    	h2 = new Horario(LocalDate.of(2021, 3, 15),e,new ArrayList<Sesion>());
    	assertFalse(horarioService.dayAlreadyInSchedule(h2));
    }
    
    @Test
    public void shouldCheckToken() {
    	/*El método comprueba que no se haya pedido cita en la misma sesión en la que se pretende canjear el bono*/
        Cita cita = new Cita(c,s);		
        assertFalse(horarioService.checkTokenAptExist(cita, c.getCitas()));
        
        /*En este caso el cliente ya había pedido cita en esa sesión*/
        c.addApointment(cita);
        assertTrue(horarioService.checkTokenAptExist(cita, c.getCitas()));
    }
}
