package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Test class for {@link ClienteController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers=ClienteController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class ClienteControllerTests {

	private static final int TEST_CLIENTE_ID = 10;

	@Autowired
	private ClienteController clienteController;

	@MockBean
	private ClienteService clienteService;
        
    @MockBean
	private UserService userService;
        
    @MockBean
    private AuthoritiesService authoritiesService; 

	@Autowired
	private MockMvc mockMvc;

	private Cliente paco;

	@BeforeEach
	void setup() {

		paco = new Cliente();
		paco.setId(TEST_CLIENTE_ID);
        paco.setNombre("Paco");
        paco.setApellidos("Paquez Perez");
        paco.setNick("paquito12");
        paco.setIBAN("424535634563A");
        paco.setDireccion("C/ Falsa 123");
        paco.setCategoria(Categoria.CLIENTE);
        paco.setSuscripcion(SubType.PREMIUM);
		given(this.clienteService.findById(TEST_CLIENTE_ID).get()).willReturn(paco);

	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/clientes/new", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(view().name("clientes/createOrUpdateClientsForm"));
	}

	/*@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/new").param("firstName", "Joe").param("lastName", "Bloggs")
							.with(csrf())
							.param("address", "123 Caramel Street")
							.param("city", "London")
							.param("telephone", "01316761638"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/clientes/new")
							.with(csrf())
							.param("firstName", "Joe")
							.param("lastName", "Bloggs")
							.param("city", "London"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("owner"))
				.andExpect(model().attributeHasFieldErrors("owner", "address"))
				.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
				.andExpect(view().name("clientes/createOrUpdateClientsForm"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/clientes/find")).andExpect(status().isOk()).andExpect(model().attributeExists("cliente"))
				.andExpect(view().name("clientes/findClientes"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.clienteService.findOwnerByLastName("")).willReturn(Lists.newArrayList(paco, new Owner()));

		mockMvc.perform(get("/clientes")).andExpect(status().isOk()).andExpect(view().name("clientes/clientesList"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessFindFormByLastName() throws Exception {
		given(this.clienteService.findOwnerByLastName(paco.getLastName())).willReturn(Lists.newArrayList(paco));

		mockMvc.perform(get("/clientes").param("lastName", "Franklin")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/clientes/" + TEST_CLIENTE_ID));
	}

        @WithMockUser(value = "spring")
	@Test
	void testProcessFindFormNoOwnersFound() throws Exception {
		mockMvc.perform(get("/clientes").param("lastName", "Unknown Surname")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("cliente", "lastName"))
				.andExpect(model().attributeHasFieldErrorCode("cliente", "lastName", "notFound"))
				.andExpect(view().name("clientes/findClientes"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testInitUpdateOwnerForm() throws Exception {
		mockMvc.perform(get("/clientes/{ownerId}/edit", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("clientes/createOrUpdateOwnerForm"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOwnerFormSuccess() throws Exception {
		mockMvc.perform(post("/clientes/{ownerId}/edit", TEST_CLIENTE_ID)
							.with(csrf())
							.param("firstName", "Joe")
							.param("lastName", "Bloggs")
							.param("address", "123 Caramel Street")
							.param("city", "London")
							.param("telephone", "01616291589"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOwnerFormHasErrors() throws Exception {
		mockMvc.perform(post("/owners/{ownerId}/edit", TEST_CLIENTE_ID)
							.with(csrf())
							.param("firstName", "Joe")
							.param("lastName", "Bloggs")
							.param("city", "London"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("owner"))
				.andExpect(model().attributeHasFieldErrors("owner", "address"))
				.andExpect(model().attributeHasFieldErrors("owner", "telephone"))
				.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testShowOwner() throws Exception {
		mockMvc.perform(get("/owners/{ownerId}", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("owners/ownerDetails"));
	}*/

}
