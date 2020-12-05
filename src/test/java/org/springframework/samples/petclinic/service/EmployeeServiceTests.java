//package org.springframework.samples.petclinic.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.samples.petclinic.model.Bono;
//import org.springframework.samples.petclinic.model.Categoria;
//import org.springframework.samples.petclinic.model.Employee;
//import org.springframework.samples.petclinic.model.EmployeeRevenue;
//import org.springframework.samples.petclinic.model.Profession;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//@TestInstance(Lifecycle.PER_CLASS)
//public class EmployeeServiceTests {
//	@Autowired
//	protected EmployeeService employeeservice;
//	
//	@Test
//	@Transactional
//	void insertEmpleado() {
//		List<EmployeeRevenue> pagos = new ArrayList<EmployeeRevenue>();
//		Employee empleado = new Employee();
//		empleado.setApellidos("Rodríguez Cruz");
//		empleado.setCategoria(Categoria.EMPLEADO);
//		empleado.setDireccion("Calle falsa 456");
//		empleado.setIBAN("SE1232344324");
//		empleado.setNick("Paco");
//		empleado.setId(2);
//		empleado.setNombre("Francisco");
//		empleado.setPagos(pagos);
//		empleado.setProfession(Profession.CLEANER);;
//		employeeservice.save(empleado);
//	}
//	
//	@Test
//	void mostrarListaConEmpleados() {
//		List<Employee> empleados = (List<Employee>) employeeservice.findAll();
//		assertEquals(3, empleados.size());
//	}
//	
//	@Test
//	void mostrarEmpleadosPorId() {
//		Integer id = 1;
//		List<Employee> empleados = employeeservice.findByIdLista(id);
//		assertFalse(empleados.size() == 0);
//	}
//	
//	@Test
//	void mostrarEmpleadosPorProfesión() {
//		String profesion = "LIFE_GUARD";
//		List<Employee> empleados = employeeservice.findEmployeeByProfession(profesion);
//		assertFalse(empleados.size() == 0);
//	}
//	
//}
