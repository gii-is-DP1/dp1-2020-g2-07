package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.repository.BonoRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class TokenMockTest {
	
	@Mock
	private BonoRepository tokenRepo;
	
	private BonoService tokenService;
	private Bono token;
	
	@Before
    public void setUp(){
		tokenService = new BonoService(tokenRepo);
		
		token = new Bono("Prueba", 10, LocalDate.parse("2009-12-01"), LocalDate.parse("2009-12-03"), "Texto", true, null);
		
		when(tokenRepo.findTokenExists("Prueba")).thenReturn(1);
		when(tokenRepo.findTokenByCode("Prueba")).thenReturn(token);
	}
	
	@Test
	//Checks that the IncStm saves successfully
    public void shouldSave() {
	 tokenService.save(token);
	}
	
	@Test
	//Checks if the token does or not exist
	public void shouldFindTokenNoExist() {
		assertTrue(tokenService.findTokenNoExist("Pipo"));
		assertFalse(tokenService.findTokenNoExist(token.getCodigo()));
	}
	
	@Test
	//Checks if the token is in the database
	public void shouldFindTokenByCode() {
		assertTrue(tokenService.findTokenByCode(token.getCodigo()).equals(token));
	}
	
}
