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
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@WebMvcTest(controllers = ClienteController.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
    excludeAutoConfiguration= SecurityConfiguration.class)
public class ClientControllerMockTest {
    private static final int TEST_CLIENT_ID = 1;
    private static final String CLIENTS_FORM="clientes/createOrUpdateClientsForm";
    private static final String CLIENTS_LISTING="clientes/ClientsListing";
    private static final String CLIENTS_DETAILS = "clientes/clienteDetails";
    @MockBean
    private ClienteService clienteService;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private Cliente c;
    private Cliente cAux;
    private User u;
    private User uAux;
    private User uAdmin;
    private Collection<Cliente> clients;
    private Optional<Cliente> cOptional;
    private Optional<Cliente> cOptionalAux;
    private Optional<User> uOptional;
    private Optional<User> uOptionalAux;
    private Optional<Cliente> cOptionalNull;
    private Optional<User> uOptionalAdmin;
    private Pago p;
    private Admin admin;

    @BeforeEach
    public void setUp(){
        /*Authorities Cliente*/
        Authorities a = new Authorities();
        Set<Authorities> aSet = new HashSet<Authorities>();
        a.setAuthority("client");
        aSet.add(a);

        /*Authorities Admin*/
        Authorities aD = new Authorities();
        Set<Authorities> aSetD = new HashSet<Authorities>();
        aD.setAuthority("admin");
        aSetD.add(aD);

        /*Pago Cliente*/
        p = new Pago();
        p.setfEmision(LocalDate.parse("2020-11-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        p.setCantidad(30);
        p.setId(TEST_CLIENT_ID);
        ArrayList<Pago> pagos = new ArrayList<Pago>();

        /*Cliente*/
        c = new Cliente();
        u = new User("juanma","12345",true, aSet);
        c.setFirst_name("Juanma");
        c.setLast_name("Garcia");
        c.setCategory(Categoria.CLIENTE);
        c.setSuscripcion(SubType.AFTERNOON);
        c.setId(TEST_CLIENT_ID);
        c.setAddress("C/Pantomima");
        c.setEmail("jmgc101099@hotmail.com");
        c.setIBAN("ES4131905864163572187269");
        c.setUser(u);
        p.setCliente(c);
        pagos.add(p);
        c.setPagos(pagos);
        cOptional = Optional.of(c);
        uOptional = Optional.of(u);

        /*Cliente Auxiliar*/
        cAux = new Cliente();
        uAux = new User("aux","12345",true, aSet);
        cAux.setFirst_name("Aux");
        cAux.setLast_name("Aux");
        cAux.setCategory(Categoria.CLIENTE);
        cAux.setSuscripcion(SubType.AFTERNOON);
        cAux.setId(2);
        cAux.setAddress("C/Aux");
        cAux.setEmail("aux@hotmail.com");
        cAux.setIBAN("ES4131905864163572187269");
        cAux.setUser(uAux);
        cAux.setPagos(new ArrayList<Pago>());
        cOptionalAux = Optional.of(cAux);
        uOptionalAux = Optional.of(uAux);

        /*Admin*/
        admin = new Admin();
        uAdmin = new User("admin","12345", true, aSetD);
        admin.setId(1);
        admin.setUser(uAdmin);
        uOptionalAdmin = Optional.of(uAdmin);
        cOptionalNull = Optional.of(new Cliente());


        /*Clientes*/
        clients = new ArrayList<Cliente>();
        clients.add(c);
        clients.add(cAux);

        given(this.clienteService.findById(TEST_CLIENT_ID)).willReturn(cOptional);
        given(this.clienteService.findById(2)).willReturn(cOptionalAux);
        given(this.clienteService.clientByUsername("juanma")).willReturn(cOptional);
        given(this.clienteService.clientByUsername("admin")).willReturn(cOptionalNull);

        given(this.userService.findUser("juanma")).willReturn(uOptional);
        given(this.userService.findUser("aux")).willReturn(uOptionalAux);
        given(this.userService.findUser("admin")).willReturn(uOptionalAdmin);
    }

    @WithMockUser(value = "spring")
    @Test
    public void getNewClientFormSucces() throws Exception{
        mockMvc.perform(get("/clientes/new")).andExpect(status().isOk())
            .andExpect(model().attributeExists("cliente"))
            .andExpect(model().attributeExists("user"))
            .andExpect(view().name(CLIENTS_FORM));
    }

    @WithMockUser(value = "spring")
    @Test
    public void postNewClientFormSuccess() throws Exception {
        mockMvc.perform(post("/clientes/new")
            .param("first_name", "Celes").param("last_name", "Walt")
            .with(csrf())
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("email", "pikachu_1@gmail.com")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring")
    @Test
    public void postNewClientFormFail() throws Exception {
        mockMvc.perform(post("/clientes/new")
            .param("first_name", "Celes4").param("last_name", "Walt6")
            .with(csrf())
            .param("address", "")
            .param("IBAN", "XXXXX_XXXX_xxx")
            .param("email", "not_and_email"))
            .andExpect(model().attributeHasErrors("cliente"))
            .andExpect(model().attributeHasFieldErrors("cliente","first_name"))
            .andExpect(model().attributeHasFieldErrors("cliente","last_name"))
            .andExpect(model().attributeHasFieldErrors("cliente","address"))
            .andExpect(model().attributeHasFieldErrors("cliente","IBAN"))
            .andExpect(model().attributeHasFieldErrors("cliente","email"))
            .andExpect(view().name(CLIENTS_FORM));
    }

    @WithMockUser(value = "juanma")
    @Test
    public void showClientDetails() throws Exception {
        mockMvc.perform(get("/clientes/{clientId}", TEST_CLIENT_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("cliente"))
            .andExpect(view().name(CLIENTS_DETAILS));
    }

    @WithMockUser(value = "juanma")
    @Test
    public void showClientDetailsWithoutPermission() throws Exception {
        mockMvc.perform(get("/clientes/{clientId}", 2))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/clientes/1"));
    }

    @WithMockUser(value = "juanma")
    @Test
    public void getUpdateClientFormSucces() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/edit", TEST_CLIENT_ID))
            .andExpect(model().attributeExists("cliente"))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_FORM));
    }

    @WithMockUser(value = "juanma")
    @Test
    public void getUpdateClientFormNoAccess() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/edit", 2))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getUpdateClientFormNotExist() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/edit", 3))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postUpdateClientSucces() throws Exception{
        mockMvc.perform(post("/clientes/{clienteId}/edit", TEST_CLIENT_ID)
            .param("first_name", "Celes").param("last_name", "Walt")
            .with(csrf())
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("suscripcion","AFTERNOON")
            .param("email", "pikachu_1@gmail.com")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postUpdateClientFail() throws Exception{
        mockMvc.perform(post("/clientes/{clienteId}/edit", TEST_CLIENT_ID)
            .param("first_name", "Celes4").param("last_name", "Walt")
            .with(csrf())
            .param("address", "C/From the hood ma boy")
            .param("IBAN", "ES2620952473193739231755")
            .param("suscripcion","AFTERNOON")
            .param("email", "xxxxxxx_xxxxx")
            .param("user.username", "hola")
            .param("user.password", "12345"))
            .andExpect(model().attributeHasErrors("cliente"))
            .andExpect(model().attributeHasFieldErrors("cliente","first_name"))
            .andExpect(model().attributeHasFieldErrors("cliente","email"))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_FORM));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteClientSucces() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/delete", TEST_CLIENT_ID))
            .andExpect(model().attribute("message","Client deleted"))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getDeleteClientNotExist() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/delete",3))
            .andExpect(model().attribute("message","That client doesnt exist"))
            .andExpect(status().isOk())
            .andExpect(view().name(CLIENTS_LISTING));
    }

    @WithMockUser(value = "admin")
    @Test
    public void getNewPayFormSucces() throws Exception{
        mockMvc.perform(get("/clientes/{clienteId}/newPay", TEST_CLIENT_ID))
            .andExpect(model().attributeExists("pago"))
            .andExpect(model().attributeExists("cliente"))
            .andExpect(status().isOk())
            .andExpect(view().name("pay/payForm"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewPayFormSucces() throws Exception{
        mockMvc.perform(post("/clientes/{clienteId}/newPay", TEST_CLIENT_ID)
            .param("fEmision","2021-02-14")
            .param("cantidad", "30")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/clientes/1"));
    }

    @WithMockUser(value = "admin")
    @Test
    public void postNewPayFormMonthAlreadyPayed() throws Exception{
        mockMvc.perform(post("/clientes/{clienteId}/newPay", TEST_CLIENT_ID)
            .param("fEmision","2020-11-15")
            .param("cantidad", "30")
            .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(view().name("pay/payForm"));
    }
}
