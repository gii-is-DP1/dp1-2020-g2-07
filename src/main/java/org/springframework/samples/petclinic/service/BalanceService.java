package org.springframework.samples.petclinic.service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
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

	public static String getNombreMes(LocalDate fecha) {
		Month mes = fecha.getMonth();
		String nombre_mes = mes.toString();
		return nombre_mes;
	}
	
	public static String getAnyo(LocalDate fecha) {
		Integer anyo = fecha.getYear();
		String anyo_text = anyo.toString();
		return anyo_text;
	}
	
	
	//Comprueba si hoy es día 1, para calcular el balance del mes pasado
	public static boolean diaDeBalance() {
		Boolean tocaBalance = false;
		SimpleDateFormat formatter= new SimpleDateFormat("dd");
		Date hoy_millis = new Date(System.currentTimeMillis());
		String hoy = formatter.format(hoy_millis);
		if(hoy.equals("01")) {
			tocaBalance = true;
		}
		return tocaBalance;
	   }
	
	//Devuelve el día 1 del mes previo (Inicio del rango)
	public static LocalDate getPrimerDiaMesPrevio() {
		Date previo = Date.from(ZonedDateTime.now().minusMonths(1).withDayOfMonth(1).toInstant());
		DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		String primer_dia_mes_previo_s = formatter.format(previo);
		LocalDate primer_dia_mes_previo = LocalDate.parse(primer_dia_mes_previo_s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return primer_dia_mes_previo;
	}
	
	//Devuelve el último día del mes (Fin del rango)
	public static LocalDate getUltimoDiaMes(LocalDate selec) {
		LocalDate ultimo = selec.withDayOfMonth(selec.getMonth().length(selec.isLeapYear()));
		return ultimo;
	}

}
