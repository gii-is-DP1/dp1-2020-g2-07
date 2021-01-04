package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ClienteServiceTests {

    @Autowired
    private ClienteService clienteservice;

    @Before
    public void init() {

        Cliente c = new Cliente();
        User u = new User();
        u.setUsername("juanma");
        u.setPassword("12345");
        c.setFirst_name("Juanma");
        c.setLast_name("Garcia");
        c.setCategory(Categoria.CLIENTE);
        c.setId(1);
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        clienteservice.save(c, "nuevo");
    }

    @Test
    public void tryToFindClientByUsername() {
        /*Prueba a usar la funcion clientByUsername que debe devolver un Optional<Cliente>
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

    @Test
    public void findClientByID(){
        /*Puesto que solo hay un cliente añadido si preguntamos por el ID nº 1 nos devolvera a juanma*/

        Optional<Cliente> c = clienteservice.findById(1);
        assertEquals("juanma", c.get().getUser().getUsername());
    }

    @Test
    @Transactional
    public void shouldEditClient(){
        /*Pide el cliente guardado en init le cambio el nombre y se guarda, para confirmar que no se ha creado
        * otro cliente, para asegurarse que no se ha creado otro cliente se vuelve a contar el numero total y se comprueba
        * que efectivamente el nombre es el nuevo*/

        Cliente c = clienteservice.findById(1).get();
        c.setFirst_name("Pedro");
        clienteservice.save(c, "edit");

        Collection<Cliente> clients = clienteservice.findAll();
        assertTrue(clients.size() == 1);
        assertEquals("Pedro", clients.iterator().next().getFirst_name());
    }
}
