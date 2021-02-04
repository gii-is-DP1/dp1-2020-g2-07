package org.springframework.samples.petclinic.service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.repository.BalanceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	//Comprueba si hoy es dia 1 del mes a las 00:00
	@Scheduled(cron = "0 0 0 1 */1 *")
    public void checkStmDay() {
    	LocalDate day = getPrimerDiaMesPrevio();
    	String month = day.getMonth().toString();
    	String year = getAnyo(day);
    	
    	createBalance(day,month,year);
    	log.info(String.format("Income Statement of previous month has been created"));
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

	public Integer getTokens (LocalDate date_start, LocalDate date_end, Boolean used) {
		Collection<Bono> total = balanceRepo.findUsedTokensByMonth(date_start,date_end, used);
		Iterator<Bono> iterator = total.iterator();
		Integer res = 0;
        while (iterator.hasNext()) {
        	res = res + iterator.next().getPrecio();
        }
		return res;
	}

	//Collections for the income breakdown
	public Collection<Bono> getTokensData (LocalDate date_start, LocalDate date_end) {
		return balanceRepo.findUsedTokensByMonth(date_start,date_end, true);
	}

	public Collection<Pago> getSubsData (LocalDate date_start, LocalDate date_end) {
		return balanceRepo.findSubsByMonth(date_start, date_end);
	}

	public Collection<EmployeeRevenue> getSalariesData (LocalDate date_start, LocalDate date_end) {
		return balanceRepo.findSalariesByMonth(date_start, date_end);
	}
	
	public void createBalance(LocalDate day_one, String month, String year) {
    	LocalDate day_last = getUltimoDiaMes(day_one);
    	List<Employee> l_e = new ArrayList<Employee>();
    	
    	Integer subs = getSubs(day_one, day_last);
    	Integer tokens = getTokens(day_one, day_last, true);
    	Integer salaries = getSalaries(day_one, day_last);
    	Balance b = new Balance(month, year, subs, tokens, salaries, l_e);
    	save(b);
    	log.info("Income Statement with ID "+b.getId()+" created");
    }
	
	public String createStats(Balance b) {
	    Gson gsonObj = new Gson();
	    Map<Object,Object> map = null;
	    List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
	    	
	    map = new HashMap<Object,Object>(); map.put("label", "Subs"); map.put("y", b.getSubs()); list.add(map);
	    map = new HashMap<Object,Object>(); map.put("label", "Tokens"); map.put("y", b.getBonos()); list.add(map);
	    map = new HashMap<Object,Object>(); map.put("label", "Salaries"); map.put("y", -b.getSalaries()); list.add(map);
	    map = new HashMap<Object,Object>(); map.put("label", "Gross Income"); map.put("isIntermediateSum", true); map.put("color", "#55646e"); list.add(map);
	    	
	    String dataPoints = gsonObj.toJson(list);
	    log.info("GSON object created for Income Statement with ID "+ b.getId());
	    return dataPoints;
	}

}
