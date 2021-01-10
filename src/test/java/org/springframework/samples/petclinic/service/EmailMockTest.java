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
import org.springframework.samples.petclinic.repository.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EmailMockTest {
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
    private Email email;

    @Before
    public void setUp(){
        clienteService = new ClienteService(clienteRepository, userService, authoritiesService
        ,emailService);
        c = new Cliente();
        u = new User();

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

        email = new Email();
        email.setAddress(new String[]{"everybodyebola@gmail.com"});
        email.setSubject("prueba");
        email.setBody("hola probando");
    }

    @Test
    public void shouldSendMail() {
        emailService.sendMail(email);
    }
}
