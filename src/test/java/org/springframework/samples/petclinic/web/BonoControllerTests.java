package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers=BonoController.class,
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
	excludeAutoConfiguration= SecurityConfiguration.class)

class BonoControllerTests{
	private static final int TEST_BONO_ID = 1;
	
	@Autowired
	private BonoController bonocontroller;
	
	@MockBean
	private BonoService bonoservice;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Bono diego = new Bono();
		diego.setCodigo("QWERT1");
		diego.setDescripcion("Bono descuento para Diego");
		diego.setDuracion(LocalDate.of(2021, 11, 03));
		diego.setId(1);
		diego.setPrecio(5);
		
		Bono miguel = new Bono();
		miguel.setCodigo("KJBFDG23");
		miguel.setDescripcion("Bono descuento para Miguel");
		miguel.setDuracion(LocalDate.of(2021, 02, 13));
		miguel.setId(2);
		miguel.setPrecio(3);
		
		given(this.bonoservice.findBonos()).willReturn(Lists.newArrayList(diego,miguel));
//		given(this.bonoservice.findBonoById(TEST_BONO_ID)).willReturn(new Bono());
	
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitNewBonoForm() throws Exception{
		mockMvc.perform(get("/bonos/new", TEST_BONO_ID)).andExpect(status().isOk()).andExpect(view().name("bonos/createOrUpdateBonosForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowBonosListHtml() throws Exception{
		mockMvc.perform(get("/bonos")).andExpect(status().isOk()).andExpect(model().attributeExists("bonos")).andExpect(view().name("bonos/BonosListing"));
		}
	
	
}