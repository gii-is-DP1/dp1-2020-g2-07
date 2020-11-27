package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    public static final String BALANCE_LISTING="balances/BalancesListing";
    
    @Autowired
    BalanceService balanceService;
    
    @GetMapping
    public String listClients(ModelMap model){
    	LocalDate day_one = balanceService.getPrimerDiaMesPrevio();
    	String month = balanceService.getNombreMes(day_one);
    	String year = balanceService.getAnyo(day_one);
    	
    	if(balanceService.diaDeBalance() && !balanceService.balanceExists(month, year)) {
    		createBalance(day_one,month,year);
    	}
        model.addAttribute("balances", balanceService.findAll());
        return BALANCE_LISTING;
    }
    
    public void createBalance(LocalDate day_one, String month, String year) {
    	LocalDate day_last = balanceService.getUltimoDiaMes(day_one);
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String day_one_string = formatter.format(day_one);
    	String day_last_string = formatter.format(day_last);
    	
    	Balance b = new Balance();
    	b.setMonth(month);
    	b.setYear(year);
    	b.setSubs(balanceService.getSubs(day_one_string, day_last_string));
    	b.setBonos(100);
    	b.setSalaries(balanceService.getSalaries(day_one, day_last));
    	b.setMante(100);
    	balanceService.save(b);
    }
}
