package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.EmployeeRepository;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EmployeeMockTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserService userService;

    @Mock
    private AuthoritiesService authoritiesService;

    private EmployeeService employeeService;
    private Employee e;
    private User u;
    private Collection<Employee> employees;
    private Optional<Employee> eOptional;
    private EmployeeRevenue revenue;

    @Before
    public void setUp(){
        employeeService = new EmployeeService(employeeRepository, userService, authoritiesService);
        u = new User();
        e = new Employee();
        employees = new ArrayList<Employee>();

        u.setUsername("Lyle");
        u.setPassword("hola12345");
        u.setEnabled(true);
        e.setFirst_name("Lyle");
        e.setLast_name("Walt");
        e.setCategory(Categoria.EMPLEADO);
        e.setProfession(Profession.MASSAGIST);
        e.setDOB(LocalDate.of(2000, 1, 1));
        e.setId(1);
        e.setAddress("C/Pantomima");
        e.setEmail("jmgc101099@hotmail.com");
        e.setIBAN("ES4131905864163572187269");
        e.setUser(u);
        e.setSalaries(new ArrayList<EmployeeRevenue>());
        employees.add(e);

        revenue = new EmployeeRevenue(e, LocalDate.parse("2020-11-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
        		LocalDate.parse("2020-11-28", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 60, 510);

        eOptional = Optional.of(e);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeRepository.findById(1)).thenReturn(eOptional);
        when(employeeRepository.save(e)).thenReturn(e);
    }

    @Test
    public void shouldSave(){
        /*Comprobamos que al usar la función save del service llama a las funciones implicadas ademas de configurar
        el mock para que actue como el repositorio*/

        employeeService.save(e);
        verify(userService).saveUser(e.getUser());
        verify(authoritiesService).saveAuthorities(e.getUser().getUsername(),"employee");
    }

    @Test
    public void shouldFindAllEmployee(){
        /*Le decimos al mock que cuando llame a finAll() del repositorio nos de una Collection<Employee>*/

        Collection<Employee> employeesExample = this.employeeService.findAll();

        assertThat(employeesExample).hasSize(1);
        assertThat(employeesExample.iterator().next().getFirst_name()).isEqualTo("Lyle");
    }

    @Test
    public void shouldFindEmployeeById(){
        /*Comprobamos que al llamar a la función con un 1 nos devuelva un Optional<Employee> con contenido tal cual le hemos indicado en el mock
        * mientras que al llamar con un 2 el Optional este vacío*/

        Optional<Employee> eOptionalExample1 = this.employeeService.findById(1);
        assertTrue(eOptionalExample1.isPresent());

        Optional<Employee> eOptionalExample2 = this.employeeService.findById(2);
        assertFalse(eOptionalExample2.isPresent());
    }

    @Test
    public void shouldFindByBdayMonth(){
        /*Comprobamos que al llamar a la función con JANUARY nos devuelva un Collection<Employee> no vacio, y al llamarla con JUNE nos devuelva un
        Collection<Employee> vacio*/
        Collection<Employee> e1 = employeeService.findEmployeeByBirthdayMonth(Month.JANUARY);
        assertFalse(e1.isEmpty());

        Collection<Employee> e2 = employeeService.findEmployeeByBirthdayMonth(Month.JUNE);
        assertTrue(e2.isEmpty());
    }

    @Test
    public void shouldDeleteEmployee(){
        /*Comprobamos que al llamar al delete, tambien llama a las funciones implicado de los otros service*/

        this.employeeService.delete(e);
        verify(userService).delete(e.getUser());
    }

    @Test
    public void shouldAddRevenue(){
        /*Comprobamos que al añadir un salario y guardarlo, el numeor total de salarios ha crecido en 1, ademas de llamar a funciones implicadas de otros service*/

        assertTrue(e.getSalaries().size() == 0);

        this.employeeService.addSalaryToEmployee(1, revenue);
        assertTrue(e.getSalaries().size() == 1);
        verify(userService).saveUser(e.getUser());
        verify(authoritiesService).saveAuthorities(e.getUser().getUsername(),"employee");
    }

    @Test
    public void tryFindEmployeeByUsername(){
        /*Miramos que al buscar un empleado por su usuario este nos sea devuelto si existe en caso de no exixtir el isPresent sera false*/

        Optional<Employee> e1 = this.employeeService.employeeByUsername("Lyle");
        assertTrue(e1.isPresent());
        assertThat(e1.get().getUser().getUsername().equals("Lyle"));

        Optional<Employee> e2 = this.employeeService.employeeByUsername("Inventado");
        assertFalse(e2.isPresent());
    }
}
