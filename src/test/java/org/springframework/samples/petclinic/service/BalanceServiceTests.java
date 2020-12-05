
package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class BalanceServiceTests {
	@Autowired
	protected BalanceService balanceservice;
	
	@Test
	@Transactional
	void insertBalance() {
		Balance balance = new Balance();
		balance.setBonos(2);
		balance.setId(1);
		balance.setMante(12);
		balance.setMonth("Enero");
		balance.setSalaries(35);
		balance.setSubs(21);
		balance.setYear("2005");
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
		Integer subs = balanceservice.getSubs("2020-03-21", "2021-01-21");
		assertFalse(subs == 0);
	}
	
	@Test
	void mostrarCantidadDeSalarios() {
		Integer salarios = balanceservice.getSalaries(LocalDate.of(2020, 02, 01), LocalDate.of(2021, 01, 01));
		assertFalse(salarios == 0);
	}

}