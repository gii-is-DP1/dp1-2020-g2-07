package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    public static final String BALANCE_LISTING="balances/BalancesListing";
    public static final String BALANCE_EMPLOYEE_EDIT="balances/BalancesEmplEdit";
    
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
    		createBalance(day_one,month,year);
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
			return BALANCE_LISTING;
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
    	}else {
    		model.addAttribute("message", "Either de employee or the statement can´t be found, try again");
    	}
		return listStatement(model);
    }
    
    @GetMapping("/{balancesId}")
	public ModelAndView showBalance(@PathVariable("balancesId") int balanceId, ModelMap model, @AuthenticationPrincipal User user) {
    	ModelAndView mav = new ModelAndView("balances/balanceDetails");
    	Balance b = balanceService.findById(balanceId).get();
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
    		listStatement(model);
    	}else {
    		String dataPoints = createStats(b);
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
        	
    		
    		mav.addObject(this.balanceService.findById(balanceId).get());
    	}
		return mav;
	}
    
    public void createBalance(LocalDate day_one, String month, String year) {
    	LocalDate day_last = balanceService.getUltimoDiaMes(day_one);
    	List<Employee> l_e = new ArrayList<Employee>();
    	/*Employee e = employeeService.findEmployeeByUsername("miguel").get();
    	l_e.add(e);
    	/*Employee ep = employeeService.findEmployeeByUsername("pedro").get();
    	l_e.add(ep);*/
    	
    	
    	
    	Integer subs = balanceService.getSubs(day_one, day_last);
    	Integer tokens = balanceService.getTokens(day_one, day_last, true);
    	Integer salaries = balanceService.getSalaries(day_one, day_last);
    	Balance b = new Balance(month, year, subs, tokens, salaries, l_e);
    	balanceService.save(b);
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
    	return dataPoints;
    }
    
    
}
