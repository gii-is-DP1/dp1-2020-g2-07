package org.springframework.samples.petclinic.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
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

    public Optional<Balance> findById(Integer id){
        return balanceRepo.findById(id);
    }

    public void delete(Balance balance) {
    	balanceRepo.deleteById(balance.getId());
    }

    public void save(@Valid Balance balance){
    	balanceRepo.save(balance);
    }

    public List<Balance> findByIdLista(int id) {
		return balanceRepo.findById(id);
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
		if(hoy.equals("29")) {
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
		int res = 0;
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

	public Balance getBalanceById (Integer id) {
		Balance b = balanceRepo.findBalanceById(id);
		return b;
	}
	
	public int getTokens (LocalDate date_start, LocalDate date_end, Boolean used) {
		Collection<Bono> total = balanceRepo.findUsedTokensByMonth(date_start,date_end, used);
		Iterator<Bono> iterator = total.iterator();
		int res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getPrecio();
        }
		return res;
	}
	
	public List<Bono> getTokensData (LocalDate date_start, LocalDate date_end) {
		Collection<Bono> total = balanceRepo.findUsedTokensByMonth(date_start,date_end, true);
		List<Bono> list = new ArrayList<Bono>();
		list.addAll(total);
		return list;
	}
	
	public List<Pago> getSubsData (LocalDate date_start, LocalDate date_end, DateTimeFormatter formatter) {	
		Collection<Pago> total = balanceRepo.findSubsByMonth(date_start, date_end);
		List<Pago> list = new ArrayList<Pago>();
		list.addAll(total);
		return list;
	}
	
	public List<EmployeeRevenue> getSalariesData (LocalDate date_start, LocalDate date_end) {
		Collection<EmployeeRevenue> total = balanceRepo.findSalariesByMonth(date_start, date_end);
		List<EmployeeRevenue> list = new ArrayList<EmployeeRevenue>();
		list.addAll(total);
		return list;
	}

}
