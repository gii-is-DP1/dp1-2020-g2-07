package org.springframework.assertj;

import org.junit.Test;
import org.springframework.samples.petclinic.model.*;

public class ClientAssertTest extends AbstractClienteAssert {
    Cliente c;
    User u;
    Pago p;

    @Before
    public void setUp() {
        c = new Cliente();
        u = new User();
        p = new Pago();

        u.setUsername("juanma");
        u.setPassword("12345");
        u.setEnabled(true);
        c.setFirst_name("Juanma");
        c.setLast_name("Garcia");
        c.setCategory(Categoria.CLIENTE);
        c.setSuscripcion(SubType.AFTERNOON);
        c.setId(1);
        c.setDOB(LocalDate.of(2000, 1, 1));
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        c.setPagos(new ArrayList<Pago>());

        p.setfEmision(LocalDate.parse("2020-11-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        p.setCantidad(30);
        p.setCliente(c);
        p.setId(1);
    }
}
