  
package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class ClienteServiceTests {

	@Autowired
	protected ClienteService clienteservice;
	
	@Transactional
	void insertCliente() {
		Pago pago = new Pago();
		Cliente cliente = new Cliente();

		cliente.setSuscripcion(SubType.MATINAL);
		cliente.setApellidos("Ruiz Gordillo");
		cliente.setCategoria(Categoria.CLIENTE);
		cliente.setDireccion("Calle Falsa 123");
		cliente.setIBAN("ES1221343453234");
		cliente.setId(1);
		cliente.setNick("El Madrileño");
		cliente.setNombre("Anónimo");
		cliente.addPay(pago);
		clienteservice.save(cliente);
	}
	
	@Test
	void mostrarListaConClientes() {
		Collection<Cliente> cliente = clienteservice.findAll();
		assertEquals(3, cliente.size());
	}
	
	@Test
	void mostrarClientesPorId() {
		Integer id = 1;
		Optional<Cliente> cliente = clienteservice.findById(id);
		assertFalse(cliente.isPresent());
	}
	
	 @Test
	 @Transactional
	 void shouldUpdateCliente() {
		 Cliente cliente = this.clienteservice.findById(1).get();
	     String oldName = cliente.getNombre();
	     String newName = oldName + "X";

	     cliente.setNombre(newName);
	     this.clienteservice.save(cliente);

	     cliente = this.clienteservice.findById(1).get();
	     assertEquals(newName, cliente.getNombre());
	 }
	
	
}