package org.springframework.samples.petclinic.web;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.time.LocalDate;
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
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.EmailService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = BonoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class BonoControllerMockTest {
	private static final int TEST_TOKEN_ID = 1;
	public static final String BONOS_LISTING="bonos/BonosListing";
	public static final String REEDEM_TOKEN="bonos/ReedemToken";
	private static final int TEST_CLIENT_ID = 1;
	
	@MockBean
	private BonoService bonoservice;
	
	@MockBean
	private ClienteService clientservice;
	
	@MockBean
	private CitaService citaService;
	
	@MockBean
	private EmailService emailservice;
	
	@MockBean
	private HorarioService horarioService;
	
	@Autowired
    private MockMvc mockMvc;
	
	private Bono token;
	private Optional<Bono> oToken;
	private Admin admin;
	private User uAdmin;
	private Cliente c;
	private Optional<Cliente> cOptional;
	private User u;
	private Cita apt;
	
	@BeforeEach
    public void setUp(){
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
        
        /*Authorities Cliente*/
        Authorities a = new Authorities();
        Set<Authorities> aSet = new HashSet<Authorities>();
        a.setAuthority("client");
        aSet.add(a);
        
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
        c.setCitas(new HashSet<Cita>());
        cOptional = Optional.of(c);
        
		token = new Bono("Prueba", 10, LocalDate.parse("2009-12-01"), LocalDate.parse("2100-12-03"), "Texto", false, new Sesion());
		oToken = Optional.of(token);	

		given(this.bonoservice.findById(TEST_TOKEN_ID)).willReturn(oToken);
		given(this.clientservice.clientByUsername("juanma")).willReturn(cOptional);
		given(this.bonoservice.findTokenNoExist(token.getCodigo())).willReturn(false);
		given(this.bonoservice.findTokenNoExist("Noup")).willReturn(true);
		given(this.bonoservice.findTokenByCode(token.getCodigo())).willReturn(token);
		given(this.horarioService.checkTokenAptExist(apt, c.getCitas())).willReturn(true);
	}
	
	@WithMockUser(value = "admin1")
    @Test
    public void showTokenListing() throws Exception{
        mockMvc.perform(get("/bonos/"))
            .andExpect(model().attributeExists("bonos"))
            .andExpect(status().isOk())
            .andExpect(view().name(BONOS_LISTING));
    }
	
	@WithMockUser(value = "admin1")
    @Test
    public void getDeleteTokenSuccess() throws Exception{
        mockMvc.perform(get("/bonos/{id}/delete", TEST_TOKEN_ID))
            .andExpect(model().attribute("message","The token has been deleted"))
            .andExpect(status().isOk())
            .andExpect(view().name(BONOS_LISTING));
    }
	
	@WithMockUser(value = "admin1")
    @Test
    public void getDeleteTokenFailure() throws Exception{
        mockMvc.perform(get("/bonos/{id}/delete", 3))
            .andExpect(model().attribute("message","The token you are trying to delete doesn´t exist"))
            .andExpect(status().isOk())
            .andExpect(view().name(BONOS_LISTING));
    }
	
    @WithMockUser(value = "juanma")
    @Test
    public void getRedeemTokenSucces() throws Exception{
        mockMvc.perform(get("/bonos/redeem_token"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("tokencode"))
            .andExpect(model().attributeDoesNotExist("message", "You need to log in to access this view"))
            .andExpect(status().isOk())
            .andExpect(view().name(REEDEM_TOKEN));
    }
    
    @WithMockUser(value = "juanma")
    @Test
    public void postRedeemTokenSucces() throws Exception{
        mockMvc.perform(post("/bonos/redeem_token")
            .param("code", "Prueba")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attributeDoesNotExist("message"))
            .andExpect(view().name("redirect:/clientes/"+TEST_CLIENT_ID));
    }
    
    @WithMockUser(value = "juanma")
    @Test
    public void postRedeemTokenFailureTokenNoExist() throws Exception{
        mockMvc.perform(post("/bonos/redeem_token")
            .param("code", "Noup")
            .with(csrf()))
        	.andExpect(model().attribute("message", "This token doesn´t exist"))
        	.andExpect(status().isOk())
        	.andExpect(view().name(REEDEM_TOKEN));
    }
    
    @WithMockUser(value = "juanma")
    @Test
    public void postRedeemTokenFailureTokenAlreadyUsed() throws Exception{
    	token.setUsado(true);
        mockMvc.perform(post("/bonos/redeem_token")
            .param("code", "Prueba")
            .with(csrf()))
        	.andExpect(model().attribute("message", "This token has expired or has already been used"))
        	.andExpect(status().isOk())
        	.andExpect(view().name(REEDEM_TOKEN));
    }
    
    @WithMockUser(value = "juanma")
    @Test
    public void postRedeemTokenFailureTokenExpiredStart() throws Exception{
    	token.setDate_start(LocalDate.now().plusDays(1));;
        mockMvc.perform(post("/bonos/redeem_token")
            .param("code", "Prueba")
            .with(csrf()))
        	.andExpect(model().attribute("message", "This token has expired or has already been used"))
        	.andExpect(status().isOk())
        	.andExpect(view().name(REEDEM_TOKEN));
    }
    
    @WithMockUser(value = "juanma")
    @Test
    public void postRedeemTokenFailureTokenExpiredEnd() throws Exception{
    	token.setDate_end(LocalDate.now().minusDays(1));
        mockMvc.perform(post("/bonos/redeem_token")
            .param("code", "Prueba")
            .with(csrf()))
        	.andExpect(model().attribute("message", "This token has expired or has already been used"))
        	.andExpect(status().isOk())
        	.andExpect(view().name(REEDEM_TOKEN));
    }
}
