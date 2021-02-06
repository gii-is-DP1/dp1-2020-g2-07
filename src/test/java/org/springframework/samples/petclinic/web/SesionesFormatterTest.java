package org.springframework.samples.petclinic.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.service.HorarioService;

@ExtendWith(MockitoExtension.class)
public class SesionesFormatterTest {

	@Mock
	private HorarioService hs;
	
	private SesionesFormatter sf;
	private Horario h;
	private Sesion s;
	private Collection<Horario> horarios;
	
	
	@BeforeEach
	public void setup() {
		sf = new SesionesFormatter(hs);
		h = new Horario();
		h.setFecha(LocalDate.of(2021, 03, 14));
		s =  new Sesion();
		s.setHoraInicio(LocalTime.of(9, 00));
		s.setHoraFin(LocalTime.of(10, 00));
		s.setHorario(h);
		h.setSesiones(new ArrayList<Sesion>());
		h.addSesion(s);
		horarios = new HashSet<Horario>();
		horarios.add(h);
	}
	
	@Test
	public void testPrint() {
		String sesion = sf.print(s, Locale.getDefault());
		assertEquals("2021-03-14: From 09:00 to 10:00",sesion);
		
		/*Formato de fecha mal escrito*/
		assertNotEquals("14/03/2021: From 09:00 to 10:00",sesion);
	}
	
	@Test
	public void shouldParse() throws ParseException{
		given(this.hs.findAll()).willReturn(horarios);
		Sesion sesion = sf.parse("2021-03-14: From 09:00 to 10:00", Locale.getDefault());
		assertEquals(LocalDate.of(2021, 03, 14),sesion.getHorario().getFecha());
		
		assertThrows(ParseException.class, ()-> {sf.parse("2021-03-15: From 09:00 to 10:00", Locale.getDefault());});
	}
	
	
}
