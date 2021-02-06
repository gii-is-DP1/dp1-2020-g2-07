package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.model.RoomType;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.BalanceRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BalanceMockTest {
	
	@Mock
	private BalanceRepository balanceRepo;
	
	private BalanceService IncStmServcice;
	private Bono token;
	private Sesion sesion;
	private Sala sala;
	private Horario horario;
	private Balance b;
	private Collection<Bono> tokens;
    private Employee e;
    private User u;
    private Collection<Employee> employees;
    private EmployeeRevenue salary;
    private Collection<EmployeeRevenue> salaries;
    private Pago p;
    private Collection<Pago> payments;
    
	@Before
    public void setUp(){
		IncStmServcice = new BalanceService(balanceRepo);
		
		//Create payment
        payments = new ArrayList<Pago>();
        
        p = new Pago(LocalDate.parse("2020-11-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 30, null);
        payments.add(p);
		
        //Create employee
		u = new User();
        e = new Employee();
        employees = new ArrayList<Employee>();
        u.setUsername("Lyle");
        u.setPassword("hola12345");
        u.setEnabled(true);
        e.setFirst_name("Lyle");
        e.setLast_name("Walt");
        e.setCategory(Categoria.EMPLEADO);
        e.setProfession(Profession.MASSAGIST);
        e.setId(1);
        e.setAddress("C/Pantomima");
        e.setEmail("jmgc101099@hotmail.com");
        e.setIBAN("ES4131905864163572187269");
        e.setUser(u);
        e.setSalaries(new ArrayList<EmployeeRevenue>());
        employees.add(e);
        
        //Create salary
        salary = new EmployeeRevenue(e, LocalDate.parse("2020-11-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
        		LocalDate.parse("2020-11-28", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 60, 510);  
        
        salaries = new ArrayList<EmployeeRevenue>();
        salaries.add(salary);
		
        //Create schedule, room, session and token
		horario = new Horario(LocalDate.parse("2009-12-04"), null, new ArrayList<Sesion>());
		sala = new Sala(RoomType.LIFE_GUARD, 12, "text", null, new ArrayList<Sesion>());
		sesion = new Sesion(null, LocalTime.parse("10:00"), LocalTime.parse("12:00"), sala, horario);
		token = new Bono("Prueba", 10, LocalDate.parse("2009-12-01"), LocalDate.parse("2009-12-03"), "Texto", true, sesion);
		sala.getSesiones().add(sesion);
		horario.getSesiones().add(sesion);
		tokens = new ArrayList<Bono>();
		tokens.add(token);
		
		//Create Income Statement
		b = new Balance("DECEMBER", "2009", 100, 200, 300,new ArrayList<Employee>());
		
		
		when(balanceRepo.findUsedTokensByMonth(LocalDate.parse("2009-12-01"), LocalDate.parse("2009-12-31"), true)).thenReturn(tokens);
		when(balanceRepo.findSalariesByMonth(LocalDate.parse("2009-12-01"), LocalDate.parse("2009-12-31"))).thenReturn(salaries);
		when(balanceRepo.findSubsByMonth(LocalDate.parse("2009-12-01"), LocalDate.parse("2009-12-31"))).thenReturn(payments);
	}
	
	 @Test //Checks that the IncStm saves successfully
	    public void shouldCreateStatement() {
		IncStmServcice.createBalance(LocalDate.parse("2009-12-01"), "DECEMBER", "2009");
	 }
	 
	 @Test //Checks that the IncStm saves successfully
	    public void shouldSave() {
		 IncStmServcice.save(b);
	 }
	 
	 @Test //Checks the Last day of Month
	 public void getUltimoDiaMes() {
		 assertTrue(IncStmServcice.getUltimoDiaMes(LocalDate.parse("2009-12-01")).equals(LocalDate.parse("2009-12-31")));
	 }
	 
	 @Test //Checks the earnings from subscriptions
	 public void shouldgetSubs() {
		 LocalDate init = LocalDate.parse("2009-12-01");
		 LocalDate last = LocalDate.parse("2009-12-31");
		 assertTrue(IncStmServcice.getSubs(init, last).equals(p.getCantidad()));
	 }
	 
	 @Test //Checks the expenses in salaries
	 public void shouldGetSalaries() {
		 LocalDate init = LocalDate.parse("2009-12-01");
		 LocalDate last = LocalDate.parse("2009-12-31");
		 assertTrue(IncStmServcice.getSalaries(init, last).equals(salary.getQuantity()));
	 }
	 @Test //Checks the earnings from tokens
	 public void shouldGetTokens() {
		 LocalDate init = LocalDate.parse("2009-12-01");
		 LocalDate last = LocalDate.parse("2009-12-31");
		 assertTrue(IncStmServcice.getTokens(init, last, true).equals(token.getPrecio()));
	 }
}
