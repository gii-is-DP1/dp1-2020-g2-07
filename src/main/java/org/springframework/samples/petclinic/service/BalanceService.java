package org.springframework.samples.petclinic.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.BalanceRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {
    @Autowired
    BalanceRepository balanceRepo;

    public Collection<Balance> findAll(){
        return balanceRepo.findAll();
    }

    public Optional<Balance> findById(int id){
        return balanceRepo.findById(id);
    }

    public void delete(Balance balance) {
    	balanceRepo.deleteById(balance.getId());
    }

    public void save(@Valid Balance balance){
    	balanceRepo.save(balance);
    }
    
    //-----------------------------------------------------//

	public String getNombreMes(LocalDate fecha) {
		Month mes = fecha.getMonth();
		String nombre_mes = mes.toString();
		return nombre_mes;
	}
	
	public String getAnyo(LocalDate fecha) {
		Integer anyo = fecha.getYear();
		String anyo_text = anyo.toString();
		return anyo_text;
	}
	
	
	//Comprueba si hoy es día 1, para calcular el balance del mes pasado
	public boolean diaDeBalance() {
		Boolean tocaBalance = false;
		SimpleDateFormat formatter= new SimpleDateFormat("dd");
		Date hoy_millis = new Date(System.currentTimeMillis());
		String hoy = formatter.format(hoy_millis);
		if(hoy.equals("22")) {
			tocaBalance = true;
		}
		return tocaBalance;
	   }
	
	//Devuelve el día 1 del mes previo (Inicio del rango)
	public LocalDate getPrimerDiaMesPrevio() {
		Date previo = Date.from(ZonedDateTime.now().minusMonths(1).withDayOfMonth(1).toInstant());
		DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		String primer_dia_mes_previo_s = formatter.format(previo);
		LocalDate primer_dia_mes_previo = LocalDate.parse(primer_dia_mes_previo_s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return primer_dia_mes_previo;
	}
	
	//Devuelve el último día del mes (Fin del rango)
	public LocalDate getUltimoDiaMes(LocalDate selec) {
		LocalDate ultimo = selec.withDayOfMonth(selec.getMonth().length(selec.isLeapYear()));
		return ultimo;
	}
	
	public Integer getSubs(String init, String last) {
		Collection<Pago> total = balanceRepo.findSubsByMonth(init, last);
		Iterator<Pago> iterator = total.iterator();
		int res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getCantidad();
        }
		return res;
	}
	
	public Integer getSalaries(LocalDateTime init, LocalDateTime last) {
		Collection<EmployeeRevenue> total = balanceRepo.findSalariesByMonth(init, last);
		Iterator<EmployeeRevenue> iterator = total.iterator();
		int res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getCuantity();
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

}
