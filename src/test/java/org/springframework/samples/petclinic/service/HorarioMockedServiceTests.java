package org.springframework.samples.petclinic.service;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
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
	
	private Collection<Horario> horarios;
	
	@Before
	public void setup() {
		horarioService = new HorarioService(horarioRepo);
		
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
        
        h = new Horario();
        h.setFecha(LocalDate.of(2021, 3, 14));
        h.setEmployee(e);
        h.setId(1);
        
        horarios = new HashSet<Horario>();
        horarios.add(h);
        
        when(horarioRepo.findAll()).thenReturn(horarios);
	}
	

}
