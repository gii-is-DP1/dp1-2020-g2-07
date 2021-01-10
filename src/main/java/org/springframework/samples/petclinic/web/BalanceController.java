package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Balance;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.EmployeeRevenue;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    public static final String BALANCE_LISTING="balances/BalancesListing";
    
    @Autowired
    BalanceService balanceService;
    
    @GetMapping
    public String listClients(ModelMap model){
    	LocalDate day_one = balanceService.getPrimerDiaMesPrevio();
    	String month = day_one.getMonth().toString();
    	String year = balanceService.getAnyo(day_one);

    	if(balanceService.diaDeBalance() && !balanceService.balanceExists(month, year)) {
    		createBalance(day_one,month,year);
    	}
        model.addAttribute("balances", balanceService.findAll());
        
        return BALANCE_LISTING;
    }
    
    @GetMapping("/{balancesId}")
	public ModelAndView showBalance(@PathVariable("balancesId") int balanceId, ModelMap model) {
    	Balance b = balanceService.findById(balanceId).get();
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
    	
		ModelAndView mav = new ModelAndView("balances/balanceDetails");
		mav.addObject(this.balanceService.findById(balanceId).get());
		return mav;
	}
    
    public void createBalance(LocalDate day_one, String month, String year) {
    	LocalDate day_last = balanceService.getUltimoDiaMes(day_one);
    	
    	Integer subs = balanceService.getSubs(day_one, day_last);
    	Integer tokens = balanceService.getTokens(day_one, day_last, true);
    	Integer salaries = balanceService.getSalaries(day_one, day_last);
    	Balance b = new Balance(month, year, subs, tokens, salaries, 100);
    	balanceService.save(b);
    }
    
    public String createStats(Balance b) {
    	Gson gsonObj = new Gson();
    	Map<Object,Object> map = null;
    	List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
    	
    	map = new HashMap<Object,Object>(); map.put("label", "Subs"); map.put("y", b.getSubs()); list.add(map);
    	map = new HashMap<Object,Object>(); map.put("label", "Tokens"); map.put("y", b.getBonos()); list.add(map);
    	map = new HashMap<Object,Object>(); map.put("label", "Salaries"); map.put("y", -b.getSalaries()); list.add(map);
    	map = new HashMap<Object,Object>(); map.put("label", "Manteinance"); map.put("y", -b.getMante()); list.add(map);
    	map = new HashMap<Object,Object>(); map.put("label", "Gross Income"); map.put("isIntermediateSum", true); map.put("color", "#55646e"); list.add(map);
    	
    	String dataPoints = gsonObj.toJson(list);
    	return dataPoints;
    }
    
    
}
