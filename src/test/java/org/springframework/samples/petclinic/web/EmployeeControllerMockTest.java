package org.springframework.samples.petclinic.web;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.*;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.*;

@WebMvcTest(controllers = EmployeeController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
public class EmployeeControllerMockTest {
    private static final int TEST_EMPLOYEE_ID = 1;
    private static final String EMPLOYEES_LISTING = "employees/employeesListing";
    private static final String EMPLOYEES_FORM = "employees/createOrUpdateEmployeeForm";
    private static final String EMPLOYEES_DETAILS = "employees/employeeDetails";
    private static final String SALARY_FORM = "salary/salaryForm";

    @MockBean
    private SalaService salaService;

    @MockBean
    private HorarioService horarioService;

    @MockBean
    private UserService userService;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private Employee e;
    private User u;
    private User uAux;
    private Employee eAux;
    private Collection<Employee> employees;

    private Admin admin;
    private User uAdmin;
    private Optional<User> uOptionalAdmin;
    private Optional<User> uOptional;
    private Optional<Employee> eOptional;
    private Optional<User> uOptionalAux;
    private Optional<Employee> eOptionalAux;

    private List<Horario> horarios;
    private Horario h;

    @BeforeEach
    public void setUp(){

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
        uOptional = Optional.of(u);
        eOptional = Optional.of(e);

        /*Horarios*/
        h = new Horario(LocalDate.of(2021, 3, 14),e,new ArrayList<Sesion>());
        horarios = new ArrayList<Horario>();
        horarios.add(h);
        e.setHorarios(horarios);

        /*Employee Aux*/
        uAux = new User("aux","hola",true,aSet);
        eAux = new Employee();
        eAux.setFirst_name("Aux");
        eAux.setLast_name("Aux");
        eAux.setCategory(Categoria.EMPLEADO);
        eAux.setProfession(Profession.MASSAGIST);
        eAux.setId(2);
        eAux.setAddress("C/Pantomima");
        eAux.setEmail("jmgc101099@hotmail.com");
        eAux.setIBAN("ES4131905864163572187269");
        eAux.setUser(uAux);
        eAux.setHorarios(new ArrayList<Horario>());
        uOptionalAux = Optional.of(uAux);
        eOptionalAux = Optional.of(eAux);

        /*Employees*/
        employees = new ArrayList<Employee>();
        employees.add(e);
        employees.add(eAux);

        /*Authorities Admin*/
        Authorities aD = new Authorities();
        Set<Authorities> aSetD = new HashSet<Authorities>();
        aD.setAuthority("admin");
        aSetD.add(aD);

        /*Admin*/
        admin = new Admin();
        uAdmin = new User("admin","12345", true, aSetD);
        admin.setId(1);
        admin.setUser(uAdmin);
        uOptionalAdmin = Optional.of(uAdmin);


        given(this.horarioService.calcDays(e.getId(),"future")).willReturn(horarios);
        given(this.horarioService.calcDays(eAux.getId(),"future")).willReturn(horarios);

        given(this.employeeService.findById(TEST_EMPLOYEE_ID)).willReturn(eOptional);
        given(this.employeeService.findById(2)).willReturn(eOptionalAux);

        given(this.userService.findUser("lyle")).willReturn(uOptional);
        given(this.userService.findUser(uAdmin.getUsername())).willReturn(uOptionalAdmin);
        given(this.userService.findUser("aux")).willReturn(uOptionalAux);

        given(this.employeeService.findEmployeeByUsername("lyle")).willReturn(eOptional);
        given(this.employeeService.findEmployeeByUsername("aux")).willReturn(eOptionalAux);
    }

    @WithMockUser(value = "lyle")
    @Test
    public void showEmployeeDetailsSuccess() throws Exception {
        mockMvc.perform(get("/employees/{employeeId}", TEST_EMPLOYEE_ID))
            .andExpect(model().attributeExists("employee"))
            .andExpect(model().attributeExists("horarios"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_DETAILS));
    }

    @WithMockUser(value = "lyle")
    @Test
    public void showEmployeeDetailsWithoutPermission() throws Exception {
        mockMvc.perform(get("/employees/{employeeId}", 2))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/employees/1"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getNewEmployeetFormSucces() throws Exception{
        mockMvc.perform(get("/employees/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("employee"))
            .andExpect(view().name(EMPLOYEES_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewEmployeeFormSuccess() throws Exception{
        mockMvc.perform(post("/employees/new")
            .param("first_name", "Celes").param("last_name", "Walt")
            .with(csrf())
            .param("id","3")
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("email", "pikachu_1@gmail.com")
            .param("age","21")
            .param("profession","LIFE_GUARD")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewEmployeeFormFail() throws Exception{
        mockMvc.perform(post("/employees/new")
            .param("first_name", "Celes1").param("last_name", "Walt")
            .with(csrf())
            .param("id","3")
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "XXXXX_XXXXX")
            .param("email", "pikachu_1@gmail.com")
            .param("age","21")
            .param("profession","LIFE_GUARD")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(model().attributeHasErrors("employee"))
            .andExpect(model().attributeHasFieldErrors("employee","first_name"))
            .andExpect(model().attributeHasFieldErrors("employee","IBAN"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_FORM));
    }

    @WithMockUser(value = "lyle")
    @Test
    public void getUpdateClientFormSucces() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/edit", TEST_EMPLOYEE_ID))
            .andExpect(model().attributeExists("employee"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_FORM));
    }

    @WithMockUser(value = "lyle")
    @Test
    public void getUpdateClientFormNoAccess() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/edit", 2))
            .andExpect(view().name("redirect:/employees/1"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postUpdateEmployeeSucces() throws Exception{
        mockMvc.perform(post("/employees/{employeeId}/edit", TEST_EMPLOYEE_ID)
            .param("first_name", "Celes").param("last_name", "Walt")
            .with(csrf())
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("suscripcion","AFTERNOON")
            .param("email", "pikachu_1@gmail.com")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/employees/1"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postUpdateEmployeeFail() throws Exception{
        mockMvc.perform(post("/employees/{employeeId}/edit", TEST_EMPLOYEE_ID)
            .param("first_name", "Celes").param("last_name", "Walt1")
            .with(csrf())
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("suscripcion","AFTERNOON")
            .param("email", "__ --- __")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(model().attributeHasErrors("employee"))
            .andExpect(model().attributeHasFieldErrors("employee","last_name"))
            .andExpect(model().attributeHasFieldErrors("employee","email"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteEmployeeSucces() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/delete", 2))
            .andExpect(model().attribute("message","Employee deleted successfully!!"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteEmployeeFailHaveWork() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/delete", TEST_EMPLOYEE_ID))
            .andExpect(model().attribute("message","The employee can´t be deleted if they have work left to do"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteEmployeeFailNotExist() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/delete", 3))
            .andExpect(model().attribute("message","Cant find the employee you are looking for"))
            .andExpect(status().isOk())
            .andExpect(view().name(EMPLOYEES_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getNewSalaryFormSucces() throws Exception{
        mockMvc.perform(get("/employees/{employeeId}/newSalary", TEST_EMPLOYEE_ID))
            .andExpect(model().attributeExists("revenue"))
            .andExpect(model().attributeExists("employee"))
            .andExpect(status().isOk())
            .andExpect(view().name(SALARY_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewPayFormSucces() throws Exception{
        mockMvc.perform(post("/employees/{employeeId}/newSalary", TEST_EMPLOYEE_ID)
            .param("dateStart","2021-02-01")
            .param("dateEnd", "2021-02-28")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/employees/1"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewPayFormFailDifferentMonth() throws Exception{
        mockMvc.perform(post("/employees/{employeeId}/newSalary", TEST_EMPLOYEE_ID)
            .param("dateStart","2021-02-01")
            .param("dateEnd", "2021-03-28")
            .with(csrf()))
            .andExpect(model().attribute("message","Revenues must have a length of only 1 month"))
            .andExpect(status().isOk())
            .andExpect(view().name(SALARY_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewPayFormFailFirstDateOlder() throws Exception{
        mockMvc.perform(post("/employees/{employeeId}/newSalary", TEST_EMPLOYEE_ID)
            .param("dateStart","2021-02-25")
            .param("dateEnd", "2021-02-01")
            .with(csrf()))
            .andExpect(model().attribute("message","Start date must be before end date"))
            .andExpect(status().isOk())
            .andExpect(view().name(SALARY_FORM));
    }
}
