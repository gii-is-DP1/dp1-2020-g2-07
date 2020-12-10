package org.springframework.samples.petclinic.web;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	
	@MockBean
	private BonoService bonoservice;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Bono bono = new Bono();
		bono.setCodigo();
		bono.setDescripcion("Bono descuento para Diego");
		//bono.setDuracion(LocalDate.of(2021, 11, 03));
		bono.setId(1);
		bono.setPrecio(5);
		
		Bono miguel = new Bono();
		miguel.setCodigo();
		miguel.setDescripcion("Bono descuento para Miguel");
		//miguel.setDuracion(LocalDate.of(2021, 02, 13));
		miguel.setId(2);
		miguel.setPrecio(3);
		
		given(this.bonoservice.findBonos()).willReturn(Lists.newArrayList(bono,miguel));
//		given(this.bonoservice.findById(TEST_BONO_ID).get()).willReturn(diego);
	
	}
	
	//Iniciar creación de un bono
	@WithMockUser(value = "spring")
	@Test
	void testInitNewBonoForm() throws Exception{
		mockMvc.perform(get("/bonos/new", TEST_BONO_ID)).andExpect(status().isOk()).andExpect(view().name("bonos/createOrUpdateBonosForm"));
	}
	
	//Crear un bono correctamente
	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/bonos/new", TEST_BONO_ID)
							.with(csrf())
							.param("codigo", "QWERTY1")
							.param("precio", "3")
							.param("duracion", "2021-02-12")
							.param("descripcion", "prueba"))
				.andExpect(status().is2xxSuccessful());
	}
	
	//Crear un bono con errores
//	@WithMockUser(value = "spring")
//    @Test
//	void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/bonos/new", TEST_BONO_ID)
//						.with(csrf())
//						.param("codigo", "QWERTY1")
//						.param("duracion", "2021/02/12"))
//				//.andExpect(model().attributeHasNoErrors("owner"))
//				.andExpect(model().attributeHasErrors("bonos"))
//				.andExpect(status().isOk())					
//				.andExpect(view().name("bonos/createOrUpdateBonosForm"));
//	}
//	
	//Iniciar formulario de editar
	@WithMockUser(value = "spring")
	@Test
		void testInitUpdateForm() throws Exception {
			mockMvc.perform(get("/bonos/{bonoId}/edit", TEST_BONO_ID))
					.andExpect(status().isOk()).andExpect(model().attributeExists("bonos"))
					.andExpect(view().name("bonos/BonosListing"));
		}
	 
	//Actualizar bonos correctamente
	@WithMockUser(value = "spring")
	@Test
		void testProcessUpdateFormSuccess() throws Exception {
			mockMvc.perform(post("/bonos/{bonoId}/edit", TEST_BONO_ID)
								.with(csrf())
								.param("codigo", "QWERTY1")
								.param("precio", "3")
								.param("duracion", "2021-02-12")
								.param("descripcion", "prueba"))
				.andExpect(status().is2xxSuccessful());
	 }
	//Actualizar bonos erróneamente
//	 @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormHasErrors() throws Exception {
//		mockMvc.perform(post("/bonos/{bonoId}/edit",TEST_BONO_ID)
//							.with(csrf())
//							.param("codigo", "QWERTY1")
//							.param("duracion", "2021/02/12"))
//				//.andExpect(model().attributeHasNoErrors("owner"))
//				.andExpect(model().attributeHasErrors("bonos"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("bonos/createOrUpdateBonosForm"));
//		}
	 
	//Mostrar lista Bonos
	@WithMockUser(value = "spring")
	@Test
	void testShowBonosListHtml() throws Exception{
		mockMvc.perform(get("/bonos")).andExpect(status().isOk()).andExpect(model().attributeExists("bonos")).andExpect(view().name("bonos/BonosListing"));
		}
	
	
}