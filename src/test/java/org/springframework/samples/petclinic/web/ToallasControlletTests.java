package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Toallas;
import org.springframework.samples.petclinic.service.ToallasService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.xml.HasXPath.hasXPath;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link ToallasController}
 */
@WebMvcTest(controllers=ToallasController.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class ToallasControllerTests {

	@Autowired
	private ToallasController toallasController;

	@MockBean
	private ToallasService toallasService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

        Toallas t = new Toallas();
        t.setId(1);
        t.setCantidad(1);
		given(this.toallasService.findToallas()).willReturn(Lists.newArrayList(t));
	}
        
    @WithMockUser(value = "spring")
		@Test
	void testShowVetListHtml() throws Exception {
		mockMvc.perform(get("/toallas")).andExpect(status().isOk()).andExpect(model().attributeExists("toallas"))
				.andExpect(view().name("toallas/toallasList"));
	}	

	@WithMockUser(value = "spring")
        @Test
	void testShowVetListXml() throws Exception {
		mockMvc.perform(get("/toallas.xml").accept(MediaType.APPLICATION_XML)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
				.andExpect(content().node(hasXPath("/toallas/toallasList[id=1]/id")));
	}

}
