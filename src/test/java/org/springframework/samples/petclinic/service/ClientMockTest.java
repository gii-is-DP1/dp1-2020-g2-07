package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.ClienteRepository;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ClientMockTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private UserService userService;

    @Mock
    private EmailService emailService;

    @Mock
    private AuthoritiesService authoritiesService;

    private ClienteService clienteService;
    private Cliente c;
    private User u;
    private Collection<Cliente> clients;
    private Optional<Cliente> cOptional;
    private Pago p;

    @Before
    public void setUp(){
        clienteService = new ClienteService(clienteRepository, userService, authoritiesService,emailService);
        c = new Cliente();
        u = new User();
        clients = new ArrayList<Cliente>();
        p = new Pago();

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
        c.setPagos(new ArrayList<Pago>());
        clients.add(c);

        p.setfEmision(LocalDate.parse("2020-11-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        p.setCantidad(30);
        p.setCliente(c);
        p.setId(1);

        cOptional = Optional.of(c);

        when(clienteRepository.findAll()).thenReturn(clients);
        when(clienteRepository.findById(1)).thenReturn(cOptional);
        when(clienteRepository.save(c)).thenReturn(c);
    }

    @Test
    public void shouldFindAll(){
        Collection<Cliente> clientsExample = this.clienteService.findAll();

        assertThat(clientsExample).hasSize(1);
        assertThat(clientsExample.iterator().next().getFirst_name()).isEqualTo("Juanma");
    }

    @Test
    public void shouldFindByUsername(){
        Optional<Cliente> c1 = clienteService.clientByUsername("juanma");
        assertThat(c1.isPresent());

        Optional<Cliente> c2 = clienteService.clientByUsername("fran");
        assertFalse(c2.isPresent());
    }

    @Test
    public void shouldSave(){
        clienteService.save(c, "new");
        verify(authoritiesService).saveAuthorities("juanma", "client");
        verify(userService).saveUser(c.getUser());
        verify(emailService).sendMail(any(Email.class));
    }

    @Test
    public void shouldFindClientById(){
        Optional<Cliente> cOptionalExample1 = clienteService.findById(1);
        assertTrue(cOptionalExample1.isPresent());

        Optional<Cliente> cOptionalExample2 = clienteService.findById(2);
        assertFalse(cOptionalExample2.isPresent());
    }

    @Test
    public void shouldDeleteClient(){
        clienteService.delete(c);
        verify(userService).delete(c.getUser());
    }

    @Test
    public void shouldAddPay(){
        assertTrue(c.getPagos().size() == 0);

        clienteService.addPayToClient(1,p);

        assertTrue(c.getPagos().size() == 1);

        verify(authoritiesService).saveAuthorities("juanma", "client");
        verify(userService).saveUser(c.getUser());
    }

}
