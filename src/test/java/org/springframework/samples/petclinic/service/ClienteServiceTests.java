package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ClienteServiceTests {

    @Autowired
    protected ClienteService clienteservice;

    @Before
    public void init() {
        Cliente c = new Cliente();
        User u = new User();
        u.setUsername("juanma");
        u.setPassword("12345");
        c.setFirst_name("odfiuy");
        c.setLast_name("fkjdh");
        c.setCategory(Categoria.EMPLEADO);
        c.setId(1);
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        clienteservice.save(c, "nuevo");
    }

    @Test
    public void tryToFindClientByUsername() {
        /*Pruba a usar la funcion clientByUsername que debe devolver un Optional<Cliente>
         * en el primer caso busca el username de un cliente existente, mientras en el segundo es al inexistente*/

        Optional<Cliente> c1 = clienteservice.clientByUsername("juanma");
        assertTrue(c1.isPresent());

        Optional<Cliente> c2 = clienteservice.clientByUsername("inventado");
        assertFalse(c2.isPresent());
    }

    @Test
    public void howManyClients() {
        /*Como hemos añadido un unico cliente al llamar a la función findAll que devuelve una colleccion con todos los
         * clientes su tamaño debería de ser uno*/

        Collection<Cliente> clients = clienteservice.findAll();
        assertTrue(clients.size() == 1);
    }


}
