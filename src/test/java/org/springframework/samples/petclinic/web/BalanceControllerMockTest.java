package org.springframework.samples.petclinic.web;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Admin;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.Profession;
import org.springframework.samples.petclinic.model.StatementEmployeeUsername;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BalanceService;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = BalanceController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class BalanceControllerMockTest {
	
	private static final int TEST_STATEMENT_ID = 1;
	private static final String TEST_EMPLOYEE_USERNAME = "lyle";
	private static final int TEST_EMPLOYEE_ID = 1;
	public static final String BALANCE_EMPLOYEE_EDIT="balances/BalancesEmplEdit";
	public static final String BALANCE_LISTING="balances/BalancesListing";
	public static final String BALANCE_DETAILS="balances/balanceDetails";
	
	@MockBean
    private BalanceService IncStmServcice;
	
	@MockBean
    private EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;
	 
	private Balance b;
	private Optional<Balance> bOptional;
	private Optional<Employee> eOptional;
	
	private Employee e;
    private User u;
    private StatementEmployeeUsername us;
    
    private LocalDate init;
    private LocalDate end;
    
    private Collection<Bono> tokens;
    private Collection<Pago> pay;
    private Collection<EmployeeRevenue> salaries;
    private ArrayList<Employee> emp;
    
    private Admin admin;
    private User uAdmin;
	 
	@BeforeEach
	public void setUp(){
		//Dates
		init = LocalDate.parse("2009-12-01");
		end = LocalDate.parse("2009-12-31");
		
		//Create Income Statement
		b = new Balance("DECEMBER", "2009", 100, 200, 300,new ArrayList<Employee>());
		bOptional = Optional.of(b);
		
		/*Authorities Employee*/
        Authorities a = new Authorities();
        Set<Authorities> aSet = new HashSet<Authorities>();
        a.setAuthority("employee");
        aSet.add(a);

        /*Employee*/
        u = new User("lyle","hola",true,aSet);
        e = new Employee();
        e.setFirst_name("Lyle");
        e.setLast_name("Walt");
        e.setCategory(Categoria.EMPLEADO);
        e.setProfession(Profession.MASSAGIST);
        e.setId(TEST_EMPLOYEE_ID);
        e.setAge(21);
        e.setAddress("C/Pantomima");
        e.setEmail("jmgc101099@hotmail.com");
        e.setIBAN("ES4131905864163572187269");
        e.setUser(u);
        e.setSalaries(new ArrayList<EmployeeRevenue>());
        eOptional = Optional.of(e);
        
        emp= new ArrayList<Employee>();
        emp.add(e);
        b.setEmployee(emp);
        
        
        /*Authorities Admin*/
        Authorities aD = new Authorities();
        Set<Authorities> aSetD = new HashSet<Authorities>();
        aD.setAuthority("admin1");
        aSetD.add(aD);
        
        /*Admin*/
        admin = new Admin();
        uAdmin = new User("admin1","12345", true, aSetD);
        admin.setId(1);
        admin.setUser(uAdmin);
        
        
        /*Username for the statement*/
        us = new StatementEmployeeUsername();
        us.setUsername(TEST_EMPLOYEE_USERNAME);
        
        /*Datas*/
        tokens = new ArrayList<Bono>();
        pay = new ArrayList<Pago>();
        salaries = new ArrayList<EmployeeRevenue>();
        
		
		given(this.IncStmServcice.findById(TEST_STATEMENT_ID)).willReturn(bOptional);
		given(this.employeeService.findEmployeeByUsername(TEST_EMPLOYEE_USERNAME)).willReturn(eOptional);
		given(this.IncStmServcice.getPrimerDiaMesPrevio()).willReturn(init);
		given(this.IncStmServcice.getAnyo(init)).willReturn("2009");
		given(this.IncStmServcice.diaDeBalance()).willReturn(true);
		given(this.IncStmServcice.balanceExists("DECEMBER", "2009")).willReturn(true);
		
		given(this.IncStmServcice.getTokensData(init, end)).willReturn(tokens);
		given(this.IncStmServcice.getSalariesData(init, end)).willReturn(salaries);
		given(this.IncStmServcice.getSubsData(init, end)).willReturn(pay);
		given(this.IncStmServcice.createStats(b)).willReturn("prueba");
		
	}
	
	@WithMockUser(value = "admin1")
    @Test
	public void getAddEmployeetFormSucces() throws Exception{
        mockMvc.perform(get("/balances/{balancesId}/edit", TEST_STATEMENT_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("username"))
            .andExpect(view().name(BALANCE_EMPLOYEE_EDIT));
    }
	
	@WithMockUser(value = "admin1")
    @Test
	public void getAddEmployeetFormFailure() throws Exception{
        mockMvc.perform(get("/balances/{balancesId}/edit", 2))
            .andExpect(status().isOk())
            .andExpect(model().attribute("message","We could not find the statement you are trying to edit."))
            .andExpect(view().name(BALANCE_LISTING));
    }
	
    @WithMockUser(value = "admin1")
    @Test
    public void postAddEmployeetFormSucces() throws Exception{
        mockMvc.perform(post("/balances/{balancesId}/edit", TEST_STATEMENT_ID)
            .param("username", "lyle")
        	.with(csrf()))
            .andExpect(status().isOk())
            .andExpect(model().attribute("message","Employee is now able to consult the income statement"))
            .andExpect(view().name(BALANCE_LISTING));
    }
    
    @WithMockUser(value = "admin1")
    @Test
    public void postAddEmployeetFormFailureStatementDoesNotExist() throws Exception{
        mockMvc.perform(post("/balances/{balancesId}/edit", 2)
            .param("username", "lyle")
        	.with(csrf()))
            .andExpect(status().isOk())
            .andExpect(model().attribute("message","Either the employee or the statement can´t be found, try again"))
            .andExpect(view().name(BALANCE_LISTING));
    }
    
    @WithMockUser(value = "admin1")
    @Test
    public void postAddEmployeetFormFailureEmployeeDoesNotExist() throws Exception{
        mockMvc.perform(post("/balances/{balancesId}/edit", TEST_STATEMENT_ID)
            .param("username", "pipencio")
        	.with(csrf()))
            .andExpect(status().isOk())
            .andExpect(model().attribute("message","Either the employee or the statement can´t be found, try again"))
            .andExpect(view().name(BALANCE_LISTING));
    }
    
    @WithMockUser(value = "admin1")
    @Test
    public void showStatementDetailsSuccess() throws Exception {
        mockMvc.perform(get("/balances/{balancesId}", TEST_STATEMENT_ID))
        	.andExpect(model().attributeExists("dataPoints"))
        	.andExpect(model().attributeExists("tokens"))
            .andExpect(model().attributeExists("subs"))
            .andExpect(model().attributeExists("salaries"))
            .andExpect(model().attributeExists("balance"))
            .andExpect(status().isOk())
            .andExpect(view().name(BALANCE_DETAILS));
    }
    
    @WithMockUser(value = "lyle")
    @Test
    public void showStatementDetailsBeingEmployeeSuccess() throws Exception {
        mockMvc.perform(get("/balances/{balancesId}", TEST_STATEMENT_ID))
        	.andExpect(model().attributeExists("dataPoints"))
        	.andExpect(model().attributeExists("tokens"))
            .andExpect(model().attributeExists("subs"))
            .andExpect(model().attributeExists("salaries"))
            .andExpect(model().attributeExists("balance"))
            .andExpect(status().isOk())
            .andExpect(view().name(BALANCE_DETAILS));
    }
    
    @WithMockUser(value = "pipencio")
    @Test
    public void showStatementDetailsBeingEmployeeFail() throws Exception {
        mockMvc.perform(get("/balances/{balancesId}", TEST_STATEMENT_ID))
        	.andExpect(model().attribute("message","Employee not cualified to see this information"))
        	.andExpect(view().name(BALANCE_LISTING));
    }
}
