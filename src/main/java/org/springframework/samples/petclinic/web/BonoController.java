package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.*;
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
@RequestMapping("/bonos")
public class BonoController {
	public static final String BONOS_LISTING="bonos/BonosListing";
	public static final String REEDEM_TOKEN="bonos/ReedemToken";


	private BonoService bonoservice;
	private ClienteService clientservice;
	private CitaService citaService;
	private HorarioService horarioService;
	private EmailService emailService;

	@Autowired
	public BonoController(BonoService bonoservice, ClienteService clientservice, CitaService citaService, 
			HorarioService horarioService, EmailService emailService) {
		super();
		this.bonoservice = bonoservice;
		this.clientservice = clientservice;
		this.citaService = citaService;
		this.horarioService = horarioService;
		this.emailService = emailService;
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
			log.info("Token with ID "+ id +" was deleted");
			return listBonos(model);
		}else {
			model.addAttribute("message","The token you are trying to delete doesn´t exist");
			log.warn("Token with ID "+ id +" does not exist");
			return listBonos(model);
		}
	}

	@GetMapping("/redeem_token")
    public String redeemToken(ModelMap model,@AuthenticationPrincipal User user) {
        model.addAttribute("tokencode",new TokenCode());
        return REEDEM_TOKEN;
    }

	 @PostMapping("/redeem_token")
	 public String chargeToken(@ModelAttribute("tokencode") TokenCode code, BindingResult binding,ModelMap model,@AuthenticationPrincipal User user) {
		 Optional<Cliente> c = clientservice.clientByUsername(user.getUsername());
		 if(binding.hasErrors() || bonoservice.findTokenNoExist(code.getCode())) {
			 if(bonoservice.findTokenNoExist(code.getCode())) {
				 model.addAttribute("message", "This token doesn´t exist");
				 log.warn("Token with code "+ code +" does not exist");
			 }else {
				 model.addAttribute("message", "There has been a problem");
			 }
			 return REEDEM_TOKEN;
		 }else {
	        	Bono token = bonoservice.findTokenByCode(code.getCode());
	    		LocalDate today = LocalDate.now();
	    		if((token.getDate_start().isBefore(today) || token.getDate_start().isEqual(today)) &&
	    				(token.getDate_end().isAfter(today) || token.getDate_end().isEqual(today)) && token.getUsado() != true) {
	    			Cita apt = new Cita(c.get(), token.getSession());
	    			Set<Cita> set = c.get().getCitas();
	    			if(!horarioService.checkTokenAptExist(apt, set)) {
	    				token.setUsado(true);
			    		bonoservice.save(token);
			    		citaService.save(apt);
	    			}else {
	    				model.addAttribute("message", "The appointment either already exists or interrupts another");
	    				log.info("The appointment either already exists or interrupts another");
	    				return REEDEM_TOKEN;
	    			}
		    	}else {
		    		model.addAttribute("message", "This token has expired or has already been used");
		    		log.info("Token with code " +code+ " has expired or has already been used");
		    		return REEDEM_TOKEN;
		    	}
	     }
		 return "redirect:/clientes/" + String.valueOf(c.get().getId());
	 }

	 @GetMapping("/{bonoId}/buy")
     public String bonoBuyEmail(@PathVariable("bonoId") int bonoId, ModelMap model, @AuthenticationPrincipal User user){
         Optional<Cliente> c = clientservice.clientByUsername(user.getUsername());
         Optional<Bono> b = bonoservice.findById(bonoId);
         if(!c.isPresent() || !b.isPresent()){
             return listBonos(model);
         }else{
             Email e = new Email();
             String[] addres = {c.get().getEmail()};
             e.setAddress(addres);
             e.setSubject("Purchased token by " + c.get().getLast_name() + ", " + c.get().getFirst_name());
             e.setBody("Token with code: " + b.get().getCodigo() + ". \nFor " + b.get().getSession().getSala().getName() +" addressed at " +
                 b.get().getSession().getHorario().toString() + ". \n Remember to use it in your profile page.");
             emailService.sendMail(e);
         }
         model.addAttribute("message", "Your token has been sended to your email");
         return listBonos(model);
     }
}
