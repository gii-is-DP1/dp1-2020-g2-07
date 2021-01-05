package org.springframework.samples.petclinic.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class BalanceServiceTests {
	@Autowired
	protected BalanceService balanceservice;
	
	@Test
	@Transactional
	void insertBalance() {
		Balance balance = new Balance("JANUARY", "2005", 21, 2, 35, 12);
		balanceservice.save(balance);
	}
	
	@Test
	void mostrarListaConBalances() {
		List<Balance> balances = (List<Balance>) balanceservice.findAll();
		assertEquals(1, balances.size());
	}
	
	@Test
	void mostrarBalancesPorId() {
		Integer id = 1;
		List<Balance> balances = balanceservice.findByIdLista(id);
		assertFalse(balances.size() == 0);
	}
	
	@Test
	void mostrarNumeroDeSubs() {
		Integer subs = balanceservice.getSubs(LocalDate.parse("2020-03-21"), LocalDate.parse("2021-01-21"));
		assertFalse(subs == 0);
	}
	
	@Test
	void mostrarCantidadDeSalarios() {
		Integer salarios = balanceservice.getSalaries(LocalDate.of(2020, 02, 01), LocalDate.of(2021, 01, 01));
		assertFalse(salarios == 0);
	}

}
