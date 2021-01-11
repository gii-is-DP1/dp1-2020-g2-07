package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.TokenCode;
import org.springframework.samples.petclinic.repository.BonoRepository;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.HorarioService;
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

@Controller
@RequestMapping("/bonos")
public class BonoController {
	public static final String BONOS_LISTING="bonos/BonosListing";
	public static final String REEDEM_TOKEN="bonos/ReedemToken";
	

	private BonoService bonoservice;
	private ClienteService clientservice;
	private BonoRepository bonoRepo;
	private CitaService citaService;
	private HorarioService horarioService;

	@Autowired
	public BonoController(BonoService bonoservice, ClienteService clientservice, BonoRepository bonoRepo, CitaService citaService, HorarioService horarioService) {
		super();
		this.bonoservice = bonoservice;
		this.clientservice = clientservice;
		this.bonoRepo = bonoRepo;
		this.citaService = citaService;
		this.horarioService = horarioService;
	}

	@GetMapping
	public String listBonos(ModelMap model) {
		model.addAttribute("bonos",bonoservice.findAll());
		return BONOS_LISTING;
	}

	@GetMapping("/{id}/delete")
	public String deleteBono(@PathVariable("id") int id,ModelMap model) {
		Optional<Bono> bono=bonoservice.findById(id);
		if(bono.isPresent()) {
			bonoservice.delete(bono.get());
			model.addAttribute("message","The token has been deleted");
			return listBonos(model);
		}else {
			model.addAttribute("message","The token you are trying to delete doesn´t exist");
			return listBonos(model);
		}
	}

	@GetMapping("/redeem_token")
    public String redeemToken(ModelMap model,@AuthenticationPrincipal User user) {
        model.addAttribute("tokencode",new TokenCode());
        if(user==null) {
			  model.addAttribute("message", "You need to log in to access this view");
			  return listBonos(model);
		  }
        return REEDEM_TOKEN;
    }

	 @PostMapping("/redeem_token")
	 public String chargeToken(@ModelAttribute("tokencode") TokenCode code, BindingResult binding,ModelMap model,@AuthenticationPrincipal User user) {
		 Optional<Cliente> c = clientservice.clientByUsername(user.getUsername());
		 if(binding.hasErrors() || bonoRepo.findTokenExists(code.getCode()) == 0) {
			 if(bonoRepo.findTokenExists(code.getCode()) == 0) {
				 model.addAttribute("message", "This token doesn´t exist");
			 }else {
				 model.addAttribute("message", "There has been a problem");
			 }
			 return REEDEM_TOKEN;
		 }else {
	        	Bono token = bonoRepo.findTokenByCode(code.getCode());
	    		LocalDate today = LocalDate.now();
	    		if(token.getDate_start().isBefore(today) || token.getDate_start().isEqual(today) &&
	    				token.getDate_end().isAfter(today) || token.getDate_start().isEqual(today) && token.getUsado() != true) {
	    			Cita apt = new Cita(c.get(), token.getSession());
	    			Set<Cita> set = c.get().getCitas();
	    			if(!horarioService.checkTokenAptExist(apt, set)) {
	    				token.setUsado(true);
			    		bonoservice.save(token);
			    		citaService.save(apt);
	    			}else {
	    				model.addAttribute("message", "The appointment either already exists or interrupts another");
	    				return REEDEM_TOKEN;
	    			}
		    	}else {
		    		model.addAttribute("message", "This token has expired or has already been used");
		    		return REEDEM_TOKEN;
		    	}
	     }
		 return "redirect:/clientes/" + String.valueOf(c.get().getId());
	 }
}
