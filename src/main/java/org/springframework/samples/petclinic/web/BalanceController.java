package org.springframework.samples.petclinic.web;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    	if(balanceService.diaDeBalance()) {
    		createBalance();
    	}
        model.addAttribute("balances", balanceService.findAll());
        return BALANCE_LISTING;
    }
    
    public void createBalance() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate day_one = balanceService.getPrimerDiaMesPrevio();
    	LocalDate day_last = balanceService.getUltimoDiaMes(day_one);
    	String day_one_string = formatter.format(day_one);
    	String day_last_string = formatter.format(day_last);
    	String month = balanceService.getNombreMes(day_one);
    	String year = balanceService.getAnyo(day_one);
    	
    	Balance b = new Balance();
    	b.setMonth(month);
    	b.setYear(year);
    	b.setSubs(balanceService.getSubs(day_one_string, day_last_string));
    	b.setBonos(100);
    	b.setSalaries(500);
    	b.setMante(100);
    	balanceService.save(b);
    }
    

}
