package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bono;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.BonoService;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedSalaNameException;
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
@RequestMapping("/salas")
public class SalaController {
	public static final String BONOS_LISTING="bonos/BonosListing";
	public static final String BONOS_FORM="bonos/createToken";
	public static final String SALAS_FORM ="/salas/CreateOrUpdateSalasForm";
	public static final String SALAS_LISTING ="/salas/SalasListing";
	
	
	private SalaService salaService;
	private HorarioService hs;
	private CitaService cs;
	private ClienteService cls;
	private BonoService bonoservice;
	
	@Autowired
	public SalaController(SalaService salaService,HorarioService hs,CitaService cs, ClienteService cls, BonoService bonoservice) {
		this.salaService = salaService;
		this.hs = hs;
		this.cs = cs;
		this.cls = cls;
		this.bonoservice = bonoservice;
	}
	
	@GetMapping
	public String salasListing(ModelMap model) {
		model.addAttribute("salas", salaService.findAll());
		return SALAS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editSala(@PathVariable("id") int id,ModelMap model) {
		Optional<Sala> sala = salaService.findById(id);
		if(sala.isPresent()) {
			model.addAttribute("sala", sala.get());
			return SALAS_FORM;
		}else {
			model.addAttribute("message", "We could not find the room you are trying to edit.");
			return SALAS_LISTING;
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editSala(@PathVariable("id") int id,@Valid Sala modifiedSala, BindingResult binding,ModelMap model) {
		Optional<Sala> sala = salaService.findById(id);
		if(binding.hasErrors()) {
			return SALAS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedSala, sala.get(),"id","aforo","descripcion");
			try{
            	this.salaService.saveSala(modifiedSala);
            }catch(DuplicatedSalaNameException ex){
            	binding.rejectValue("name", "duplicate", "already exists");
                return  SALAS_FORM;
            }
			
			model.addAttribute("message", "The room was updated successfully.");
			return salasListing(model);
		}
	}
	
	
	@GetMapping("/{id}/delete")
	public String deleteSala(@PathVariable("id") int id, ModelMap model) {
		Optional<Sala> sala = salaService.findById(id);
		if(sala.isPresent()) {
			salaService.delete(sala.get());
			model.addAttribute("message", "The room was deleted successfully.");
			return salasListing(model);
		}else {
			model.addAttribute("message", "We could not find the room you are trying to delete.");
			return salasListing(model);
		}
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(ModelMap model) {
		Sala sala = new Sala();
		model.addAttribute("sala", sala);
		return SALAS_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Sala sala, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return SALAS_FORM;
		}
		else {
               try{
              	this.salaService.saveSala(sala);
                }catch(DuplicatedSalaNameException ex){
                 result.rejectValue("name", "duplicate", "already exists");
                return  SALAS_FORM;
              }
           model.addAttribute("message", "The room was created successfully.");
        	return salasListing(model);
		}
	}
	
	  @GetMapping("/{salaId}")
	  public String showSala(@PathVariable("salaId") int salaId, ModelMap model, @AuthenticationPrincipal User user) {
		  model.clear();
		  if(user==null) {
			  model.addAttribute("message", "You need to log in to access this view");
			  return salasListing(model);
		  }else {
			  Optional<Cliente> c = cls.clientByUsername1(user.getUsername());
			  model.addAttribute("sala", this.salaService.findById(salaId).get());
			  if(c.isPresent()) {
				  model.addAttribute("cliente", c.get().getId());
			      model.addAttribute("sesiones", hs.inTimeSessions(hs.availableSessions(hs.activeSessions(salaId,c.get()),c.get()), c.get()));
				  model.addAttribute("sesiones", hs.activeSessions(salaId,c.get()));
			      model.addAttribute("cita", new Cita());
			  }	  
			  return "salas/salaDetails";
			  
		  }
	  }
	  
		@PostMapping("/{salaId}")
		public String addCita(@PathVariable("salaId") int salaId,@Valid Cita cita, BindingResult binding, ModelMap model,@ModelAttribute("cliente") Cliente c) {
			Sala sala = salaService.findById(salaId).get();
			if(binding.hasErrors()) {
				model.addAttribute("sala", sala);
			    model.addAttribute("sesiones", hs.activeSessions(salaId,c));
				return "salas/salaDetails";
			}else {
				cs.save(cita);
				model.addAttribute("message", "You now have an appointment in " + sala.getName() + " " + cita.getSesion());
				return salasListing(model);
			}
		}
		
		
	@GetMapping("/{salaId}/createtoken")
	public String createtoken(@PathVariable("salaId") int salaId,ModelMap model) {
		model.addAttribute("bono", new Bono());
		model.addAttribute("session", hs.activeSessions(salaId, null));
		return BONOS_FORM;
	}
	
	@PostMapping("/{salaId}/createtoken")
	public String saveNewBono(@Valid Bono bono, BindingResult binding,ModelMap model) {
		if(binding.hasErrors()) {			
			return BONOS_FORM;
		}else {
			bono.setUsado(false);
			bono.setDate_start(LocalDate.now());
			bono.setDate_end(bono.getSession().getHorario().getFecha().minusDays(1));
			if (bono.getCodigo().isEmpty())
				bono.setCodigo();
			bonoservice.save(bono);
			model.addAttribute("message", "The token has been created successfully");			
			return salasListing(model);
		}
	}
    	
}
