package org.springframework.samples.petclinic.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.SalaService;

@ExtendWith(MockitoExtension.class)
public class SalaFormatterTest {
	
	@Mock
	private SalaService salaService;
	
	private SalaFormatter salaTypeFormatter;
	
	@BeforeEach
	void setyp() {
		salaTypeFormatter=new SalaFormatter(salaService);
	}
	
	@Test
	void testPrint() {
		Sala salaType= new Sala();
		salaType.setName("Jacuzzi");salaType.setAforo(8);
		String salaName=salaTypeFormatter.print(salaType, Locale.ENGLISH);
		assertEquals("Jacuzzi"+" (8)", salaName);
	}
	
	@Test
	void shouldParse() throws ParseException {
		Mockito.when(salaService.findAll()).thenReturn(makeRooms());
		Sala sala = salaTypeFormatter.parse("Jacuzzi (8)", Locale.ENGLISH);
		assertEquals("Jacuzzi", sala.getName());
		}
	
	@Test
	void shouldThrowParseException() throws ParseException {
		Mockito.when(salaService.findAll()).thenReturn(makeRooms());
		Assertions.assertThrows(ParseException.class, () -> {
			salaTypeFormatter.parse("Sauna (8)", Locale.ENGLISH);
		});
		}
	
	
	
	
	
	private Collection<Sala> makeRooms() {
		Collection<Sala> salas = new ArrayList<>();
		salas.add(new Sala() {
			{
				setName("Jacuzzi");
			}
		});
		salas.add(new Sala() {
			{
				setName("Relax Pool");
			}
		});
		return salas;
	}

}
