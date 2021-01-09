package org.springframework.samples.petclinic.service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.BalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    
    private BalanceRepository balanceRepo;

    public BalanceService(BalanceRepository balanceRepo) {
		super();
		this.balanceRepo = balanceRepo;
	}

	public Collection<Balance> findAll(){
        return balanceRepo.findAll();
    }

    public Optional<Balance> findById(Integer id){
        return balanceRepo.findById(id);
    }

    public void save(@Valid Balance balance){
    	balanceRepo.save(balance);
    }

    //-----------------------------------------------------//

	public String getAnyo(LocalDate fecha) {
		Integer anyo = fecha.getYear();
		String anyo_text = anyo.toString();
		return anyo_text;
	}


	//Comprueba si hoy es día 1, para calcular el balance del mes pasado
	public boolean diaDeBalance() {
		Boolean tocaBalance = false;
		Integer day_today = LocalDate.now().getDayOfMonth();
		if(day_today.equals(9)) {
			tocaBalance = true;
		}
		return tocaBalance;
	   }

	//Devuelve el día 1 del mes previo (Inicio del rango)
	public LocalDate getPrimerDiaMesPrevio() {
		Date previo = Date.from(ZonedDateTime.now().minusMonths(1).withDayOfMonth(1).toInstant());
		return previo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}


	//Devuelve el último día del mes (Fin del rango)
	public LocalDate getUltimoDiaMes(LocalDate selec) {
		LocalDate ultimo = selec.withDayOfMonth(selec.getMonth().length(selec.isLeapYear()));
		return ultimo;
	}

	public Integer getSubs(LocalDate init, LocalDate last) {
		Collection<Pago> total = balanceRepo.findSubsByMonth(init, last);
		Iterator<Pago> iterator = total.iterator();
		int res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getCantidad();
        }
		return res;
	}

	public Integer getSalaries(LocalDate init, LocalDate last) {
		Collection<EmployeeRevenue> total = balanceRepo.findSalariesByMonth(init, last);
		Iterator<EmployeeRevenue> iterator = total.iterator();
		Integer res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getQuantity();
        }
		return res;
	}

	public Boolean balanceExists (String month, String year) {
		Integer x = balanceRepo.findBalanceExists(month, year);
		Boolean res = false;
		if(x==1) {
			res=true;
		}
		return res;
	}
	
	public Integer getTokens (LocalDate date_start, LocalDate date_end, Boolean used) {
		Collection<Bono> total = balanceRepo.findUsedTokensByMonth(date_start,date_end, used);
		Iterator<Bono> iterator = total.iterator();
		Integer res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getPrecio();
        }
		return res;
	}
	
	public Collection<Bono> getTokensData (LocalDate date_start, LocalDate date_end) {
		return balanceRepo.findUsedTokensByMonth(date_start,date_end, true);
	}
	
	public Collection<Pago> getSubsData (LocalDate date_start, LocalDate date_end) {	
		return balanceRepo.findSubsByMonth(date_start, date_end);
	}
	
	public Collection<EmployeeRevenue> getSalariesData (LocalDate date_start, LocalDate date_end) {
		return balanceRepo.findSalariesByMonth(date_start, date_end);
	}

}
