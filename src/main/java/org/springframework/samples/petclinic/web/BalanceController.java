package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.StatementEmployeeUsername;
import org.springframework.samples.petclinic.service.BalanceService;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/balances")
public class BalanceController {
    public static final String BALANCE_LISTING="balances/BalancesListing";
    public static final String BALANCE_EMPLOYEE_EDIT="balances/BalancesEmplEdit";
    public static final String BALANCE_DETAILS="balances/balanceDetails";
    
    @Autowired
    BalanceService balanceService;
    
    @Autowired
    EmployeeService employeeService;
    
    @GetMapping
    public String listStatement(ModelMap model){
    	LocalDate day_one = balanceService.getPrimerDiaMesPrevio();
    	String month = day_one.getMonth().toString();
    	String year = balanceService.getAnyo(day_one);

    	if(balanceService.diaDeBalance() && !balanceService.balanceExists(month, year)) {
    		balanceService.createBalance(day_one,month,year);
    		log.info(String.format("Income Statement of previous month has been created"));
    	}
        model.addAttribute("balances", balanceService.findAll());
        return BALANCE_LISTING;
    }
    
    @GetMapping("/{balancesId}/edit")
    public String inputEmployeeToStatement(@PathVariable("balancesId") int balanceId,ModelMap model) {
    	Optional<Balance> b = balanceService.findById(balanceId);
    	if(b.isPresent()) {
    		model.addAttribute("username", new StatementEmployeeUsername());
			return BALANCE_EMPLOYEE_EDIT;
    	}else {
			model.addAttribute("message", "We could not find the statement you are trying to edit.");
			return listStatement(model);
		}
    }
    
    @PostMapping("/{balancesId}/edit")
    public String addEmployeeToStatement(@PathVariable("balancesId") int balanceId, @ModelAttribute("username") String username, BindingResult binding,ModelMap model) {
    	Optional<Employee> emp = employeeService.findEmployeeByUsername(username);
    	Optional<Balance> b = balanceService.findById(balanceId);
    	if(b.isPresent() && emp.isPresent()) {
    		Balance inc = b.get();
    		inc.getEmployee().add(emp.get());
    		balanceService.save(inc);
    		model.addAttribute("message", "Employee is now able to consult the income statement");
    		log.info("Employee with username "+ emp.get().getUser().getUsername() +" and ID "+ emp.get().getId() +" is now able to consult the statement with ID" +inc.getId());
    	}else {
    		model.addAttribute("message", "Either the employee or the statement can´t be found, try again");
    		log.warn("Either the employee or the statement can´t be found, try again");
    	}
		return listStatement(model);
    }
    
    @GetMapping("/{balancesId}")
	public String showBalance(@PathVariable("balancesId") int balanceId, ModelMap model, @AuthenticationPrincipal User user) {
    	Optional<Balance> ob = balanceService.findById(balanceId);
    	if(ob.isPresent()) {
    		Balance b = ob.get();
    		List<Employee> l = b.getEmployee();
        	Boolean able = false;
        	if(user.getUsername().equals("admin1")) {
        		able=true;
        	}else {
            	for(Employee e: l) {
            		if(e.getUser().getUsername().equals(user.getUsername())) {
            			able=true;
            		}
            	}
        	}
        	if(!able) {
        		model.addAttribute("message", "Employee not cualified to see this information");
        		log.info("Employee with username "+ user.getUsername() +" is not able to consult the statement with ID "+balanceId);
        		return listStatement(model);
        	}else {
        		String dataPoints = balanceService.createStats(b);
            	model.addAttribute("dataPoints", dataPoints);
            	
            	Integer year = Integer.valueOf(b.getYear());
        		Integer int_month = Month.valueOf(b.getMonth()).getValue();
        		LocalDate date_start = LocalDate.of(year, int_month, 1);
        		LocalDate date_end = date_start.withDayOfMonth(date_start.getMonth().length(date_start.isLeapYear()));
            	
            	Collection<Bono> tokens = balanceService.getTokensData(date_start, date_end);
            	Collection<Pago> subs = balanceService.getSubsData(date_start, date_end);
            	Collection<EmployeeRevenue> salaries = balanceService.getSalariesData(date_start, date_end);
            	model.addAttribute("tokens",tokens);
            	model.addAttribute("subs",subs);
            	model.addAttribute("salaries",salaries);
            	
        		model.addAttribute("balance", this.balanceService.findById(balanceId).get());
        		log.info("Employee with username %s consults the statement with ID %d",user.getUsername(), balanceId);
        		return BALANCE_DETAILS;
        	}
    	}else {
    		model.addAttribute("message", "We could not find the statement you are trying to see");
    		log.warn("Statement with ID "+ balanceId +" does not exist");
    		return listStatement(model);
    	}	
	}
}
