package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

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

    @Before
    public void setUp(){
        clienteService = new ClienteService(clienteRepository, userService, authoritiesService
        ,emailService);
        c = new Cliente();
        u = new User();
        clients = new ArrayList<Cliente>();

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
        clients.add(c);

        when(clienteRepository.findAll()).thenReturn(clients);
    }

    @Test
    public void shouldFind(){

        Collection<Cliente> clientExample = this.clienteService.findAll();

        assertThat(clientExample).hasSize(1);
        assertThat(clients.iterator().next().getFirst_name()).isEqualTo("Juanma");
    }

    @Test
    public void shouldFindByUsername(){
        Optional<Cliente> c1 = clienteService.clientByUsername("juanma");
        assertThat(c1.isPresent());

        Optional<Cliente> c2 = clienteService.clientByUsername("fran");
        assertFalse(c2.isPresent());
    }


}
