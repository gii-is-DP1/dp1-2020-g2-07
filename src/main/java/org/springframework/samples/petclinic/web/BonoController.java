package org.springframework.samples.petclinic.web;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.TokenCode;
import org.springframework.samples.petclinic.repository.BonoRepository;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bonos")
public class BonoController {
	public static final String BONOS_FORM="bonos/createOrUpdateBonosForm";
	public static final String BONOS_LISTING="bonos/BonosListing";
	public static final String REEDEM_TOKEN="bonos/ReedemToken";
	
	@Autowired
	BonoService bonoservice;
	
	@Autowired
	BonoRepository bonoRepo;
	
	@GetMapping
	public String listBonos(ModelMap model) {
		model.addAttribute("bonos",bonoservice.findAll());
		return BONOS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editBono(@PathVariable("id") int id,ModelMap model) {
		Optional<Bono> bono=bonoservice.findById(id);
		if(bono.isPresent()) {
			model.addAttribute("bono", bono.get());
			return BONOS_FORM;
		}else {
			model.addAttribute("message", "No podemos encontrar el bono que desea editar");
			return listBonos(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editBono(@PathVariable("id") int id, @Valid Bono modifiedBono, BindingResult binding, ModelMap model){
		Optional<Bono> bono=bonoservice.findById(id);
		if(binding.hasErrors()) {
			return BONOS_FORM;
		}else {
			modifiedBono.setCodigo();
			BeanUtils.copyProperties(modifiedBono, bono.get(),"id");
			bonoservice.save(bono.get());
			model.addAttribute("message", "Bono ha sido actualizado");
			return listBonos(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteBono(@PathVariable("id") int id,ModelMap model) {
		Optional<Bono> bono=bonoservice.findById(id);
		if(bono.isPresent()) {
			bonoservice.delete(bono.get());
			model.addAttribute("message","El Bono ha sido borrado");
			return listBonos(model);
		}else {
			model.addAttribute("message","No podemos encontrar el bono que está ntentando borrar");
			return listBonos(model);
		}
	}
	
	@GetMapping("/new")
	public String editNewBono(ModelMap model) {
		model.addAttribute("bono", new Bono());
		return BONOS_FORM;
	}
	
	@PostMapping("/new")
	public String saveNewBono(@Valid Bono bono, BindingResult binding,ModelMap model) {
		if(binding.hasErrors()) {			
			return BONOS_FORM;
		}else {
			bono.setCodigo();
			bono.setUsado(false);
			bonoservice.save(bono);
			model.addAttribute("message", "El Bono ha sido creado");			
			return listBonos(model);
		}
	}
	
	@GetMapping("/redeem_token")
    public String redeemToken(ModelMap model) {
        model.addAttribute("tokencode",new TokenCode());
        return REEDEM_TOKEN;
    }
	
	 @PostMapping("/redeem_token")
	 public String chargeToken(@Valid TokenCode code, BindingResult binding,ModelMap model) {
		 if(binding.hasErrors()) {
			 return REEDEM_TOKEN;
		 }else {
	        String token_code = code.getCode();
	        if(bonoRepo.findTokenExists(token_code) == 0) {
	        	model.addAttribute("message", "This token doesn´t exist");
	        }else {
	        	Bono b = bonoRepo.findTokenByCode(token_code);
	        	DateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
	        	Date today_millis = new Date(System.currentTimeMillis());
	        	String today_s = formatter.format(today_millis);
	    		LocalDate today = LocalDate.parse(today_s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		  
		    	if(b.getDate_start().isBefore(today) && b.getDate_end().isAfter(today) && b.getUsado() != true) {
		    		model.addAttribute("message", "Token reedemed, the appointment has been created");
		    		//CREAR CITA - POR HACER
		    		b.setUsado(true);
		    		bonoservice.save(b);
		    	}else {
		    		model.addAttribute("message", "This token has expired or has already been used");
		    	}
	        }
	     }
		 model.addAttribute("bonos",bonoservice.findAll());
		 return BONOS_LISTING;
	 }
}
