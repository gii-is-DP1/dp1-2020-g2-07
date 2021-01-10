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


import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AdminServiceTests {

    @Autowired
    private AdminService adminService;

    @Before
    public void init() {

        Admin a = new Admin();
        User u = new User();
        u.setUsername("eljefazo");
        u.setPassword("correcthorsebatterystaple");
        a.setId(1);
        a.setUser(u);
        adminService.save(a);
    }

    @Test
    public void tryToFindAdminById() {
        /*Prueba a usar la funcion findById que debe devolver un Optional<Admin>
         * en el primer caso busca el id de un admin existente, mientras en el segundo es al inexistente*/

        Optional<Admin> a1 = adminService.findById(1);
        assertTrue(a1.isPresent());

        Optional<Admin> a2 = adminService.findById(10);
        assertFalse(a2.isPresent());
    }

    @Test
    public void howManyAdmins() {
        /*Como hemos añadido un unico admin al llamar a la función findAll que devuelve una colleccion con todos los
         * admins su tamaño debería de ser uno*/

        Collection<Admin> admins = adminService.findAll();
        assertTrue(admins.size() == 1);
    }

    @Test
    public void findAdminByID(){
        /*Puesto que solo hay un admin añadido si preguntamos por el ID nº 1 nos devolvera a eljefazo*/

        Optional<Admin> a = adminService.findById(1);
        assertEquals("eljefazo", a.get().getUser().getUsername());
    }

    @Test
    @Transactional
    public void shouldEditAdmin(){
        /*Pide el admin guardado en init le cambio la contraseña y se guarda, para confirmar que no se ha creado
        * otro admin, para asegurarse que no se ha creado otro admin se vuelve a contar el numero total y se comprueba
        * que efectivamente la contraseña es la nueva*/

        Admin a = adminService.findById(1).get();
        a.getUser().setPassword("hola");

        adminService.save(a);

        Collection<Admin> admins = adminService.findAll();
        assertTrue(admins.size() == 1);
        assertEquals("hola", admins.iterator().next().getUser().getPassword());
    }
}

