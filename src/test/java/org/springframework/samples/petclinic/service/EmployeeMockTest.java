package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.samples.petclinic.repository.EmployeeRepository;


import java.time.LocalDate;
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
        revenue = new EmployeeRevenue();

        u.setUsername("Lyle");
        u.setPassword("hola12345");
        u.setEnabled(true);
        e.setFirst_name("Lyle");
        e.setLast_name("Walt");
        e.setCategory(Categoria.EMPLEADO);
        e.setProfession(Profession.MASSAGIST);
        e.setId(1);
        e.setAddress("C/Pantomima");
        e.setEmail("jmgc101099@hotmail.com");
        e.setIBAN("ES4131905864163572187269");
        e.setUser(u);
        e.setSalaries(new ArrayList<EmployeeRevenue>());
        employees.add(e);

        revenue.setEmployee(e);
        revenue.setDateStart(LocalDate.parse("2020-11-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        revenue.setDateEnd(LocalDate.parse("2020-11-28", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        revenue.setHoursWorked(60);
        revenue.setQuantity(510);

        eOptional = Optional.of(e);

        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeRepository.findById(1)).thenReturn(eOptional);
        when(employeeRepository.save(e)).thenReturn(e);
    }

    @Test
    public void shouldSave(){
        employeeService.save(e);
        verify(userService).saveUser(e.getUser());
        verify(authoritiesService).saveAuthorities(e.getUser().getUsername(),"employee");
    }

    @Test
    public void shouldFindAllEmployee(){
        Collection<Employee> employeesExample = this.employeeService.findAll();

        assertThat(employeesExample).hasSize(1);
        assertThat(employeesExample.iterator().next().getFirst_name()).isEqualTo("Lyle");
    }

    @Test
    public void shouldFindEmployeeById(){
        Optional<Employee> eOptionalExample1 = this.employeeService.findById(1);
        assertTrue(eOptionalExample1.isPresent());

        Optional<Employee> eOptionalExample2 = this.employeeService.findById(2);
        assertFalse(eOptionalExample2.isPresent());
    }

    @Test
    public void shouldDeleteEmployee(){
        this.employeeService.delete(e);
        verify(userService).delete(e.getUser());
    }

    @Test
    public void shouldAddRevenue(){
        assertTrue(e.getSalaries().size() == 0);

        this.employeeService.addSalaryToEmployee(1, revenue);
        assertTrue(e.getSalaries().size() == 1);
        verify(userService).saveUser(e.getUser());
        verify(authoritiesService).saveAuthorities(e.getUser().getUsername(),"employee");
    }

}
