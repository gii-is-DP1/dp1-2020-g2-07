package org.springframework.samples.petclinic.service;

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
    public void init(){
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
	    clienteservice.save(c,"nuevo");
    }

	@Test
    public void shouldFindClientByNickName(){
        Optional<Cliente> c = clienteservice.clientByUsername("juanma");
        assertTrue(c.isPresent());
    }

    @Test
    public void shouldNotFindClientByNickNameT(){
        Optional<Cliente> c = clienteservice.clientByUsername("inventado");
        assertFalse(c.isPresent());
    }

}
