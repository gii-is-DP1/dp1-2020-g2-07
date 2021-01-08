package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeServiceTests {
	@Autowired
	protected EmployeeService employeeservice;

	//@Test
	@Transactional
	void insertEmpleado() {
		EmployeeRevenue pagos = new EmployeeRevenue();
		Employee empleado = new Employee();
		/*empleado.setApellidos("Rodríguez Cruz");
		empleado.setCategoria(Categoria.EMPLEADO);
		empleado.setDireccion("Calle falsa 456");*/
		empleado.setIBAN("SE1232344324");
		//empleado.setNick("Paco");
		empleado.setId(2);
		//sesmpleado.setNombre("Francisco");
		empleado.addSalary(pagos);
		empleado.setProfession(Profession.CLEANER);;
		employeeservice.save(empleado);
	}

	@Test
	void mostrarListaConEmpleados() {
		List<Employee> empleados = (List<Employee>) employeeservice.findAll();
		assertEquals(3, empleados.size());
	}

	@Test
	void mostrarEmpleadosPorId() {
		Integer id = 1;
		Optional<Employee> empleados = employeeservice.findById(id);
		assertFalse(!empleados.isPresent());
	}

	@Test
	void mostrarEmpleadosPorProfesión() {
		String profesion = "LIFE_GUARD";
		List<Employee> empleados = employeeservice.findEmployeeByProfession(profesion);
		assertFalse(empleados.size() == 0);
	}

}
