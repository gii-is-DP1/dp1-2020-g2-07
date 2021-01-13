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
import org.springframework.samples.petclinic.repository.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserMockTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ClienteRepository clienteRepository;

    private UserService userService;

    @Mock
    private ClienteService clienteService;
    @Mock
    private EmployeeService employeeService;

    private Cliente c;
    private User u;
    private Collection<User> users;
    private Optional<User> uOptional;

    @Before
    public void setUp(){
      //  userService = new UserService(userRepository, clienteService, employeeService);
        c = new Cliente();
        u = new User();
        users = new ArrayList<User>();


        u.setUsername("paquito1");
        u.setPassword("hola");
        u.setEnabled(true);
        c.setFirst_name("Paco");
        c.setLast_name("Hernandez");
        c.setCategory(Categoria.CLIENTE);
        c.setSuscripcion(SubType.AFTERNOON);
        c.setId(1);
        c.setAddress("C/Falsa 123");
        c.setEmail("paco@estemailnoexiste.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        c.setPagos(new ArrayList<Pago>());
        users.add(u);

        uOptional = Optional.of(u);

        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.findById("paquito1")).thenReturn(uOptional);
        when(userRepository.save(u)).thenReturn(u);
    }

    @Test
    public void shouldFindAll(){
        Collection<User> usersExample = this.userService.findAll();

        assertThat(usersExample).hasSize(1);
        assertThat(usersExample.iterator().next().getUsername()).isEqualTo("paquito1");
    }

    @Test
    public void shouldFindByUsername(){
        Optional<User> u1 = userService.findUser("paquito1");
        assertTrue(u1.isPresent());

        Optional<User> u2 = userService.findUser("nadie");
        assertFalse(u2.isPresent());
    }

    @Test
    public void shouldSave(){
        userService.saveUser(u);
    }

    @Test
    public void shouldCheckUsers(){
        User u1 = new User();
        u1.setUsername("todo junto");
        u1.setPassword("hola");
        assertThat(userService.checkUser(u1).keySet()).contains(false);

        User u2 = new User();
        u2.setUsername("");
        u2.setPassword("");
        assertThat(userService.checkUser(u2).keySet()).contains(false);

        User u3 = new User();
        u3.setUsername("juan");
        u3.setPassword("juan");
        assertThat(userService.checkUser(u3).keySet()).contains(true);
    }

    @Test
    public void shouldDeleteUser(){
        userService.delete(u);
    }
}
