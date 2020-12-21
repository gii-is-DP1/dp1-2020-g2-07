  
package org.springframework.samples.petclinic.web;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cita;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salas")
public class SalaController {

	public static final String SALAS_FORM ="/salas/CreateOrUpdateSalasForm";
	public static final String SALAS_LISTING ="/salas/SalasListing";
	
	
	private SalaService salaService;
	private HorarioService hs;
	private CitaService cs;
	private ClienteService cls;
	
	@Autowired
	public SalaController(SalaService salaService,HorarioService hs,CitaService cs, ClienteService cls) {
		this.salaService = salaService;
		this.hs = hs;
		this.cs = cs;
		this.cls = cls;
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
			BeanUtils.copyProperties(modifiedSala, sala.get(),"id");
			this.salaService.save(modifiedSala);
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
			this.salaService.save(sala);
			model.addAttribute("message", "The room was created successfully.");
			return salasListing(model);
		}
	}
	
//    @GetMapping("/{salaId}")
//    public ModelAndView showSala(@PathVariable("salaId") int salaId) {
//        ModelAndView mav = new ModelAndView("salas/salaDetails");
//        mav.addObject(this.salasServices.findById(salaId).get());
//        Collection<Sesion> sesiones = hs.activeSessions(salaId);
//        mav.addObject("sesiones", sesiones);
//        return mav;
//    }
	

	  @GetMapping("/{salaId}")
	  public String showSala(@PathVariable("salaId") int salaId, ModelMap model, @AuthenticationPrincipal User user) {
		  if(user==null) {
			  model.addAttribute("message", "You need to log in to access this view");
			  return salasListing(model);
		  }else {
			  model.addAttribute("sala", this.salaService.findById(salaId).get());
		      Collection<Sesion> sesiones = hs.activeSessions(salaId);
		      model.addAttribute("sesiones", sesiones);
		      model.addAttribute("cita", new Cita());
//		      Cliente c = cls.findClienteByUsername(user.getUsername());
//		      model.addAttribute("clienteID",c);
		      return "salas/salaDetails"; 
		  }
	  }
	  
		@PostMapping("/{salaId}")
		public String addCita(@PathVariable("salaId") int salaId,@Valid Cita cita, BindingResult binding,ModelMap model,@AuthenticationPrincipal User user) {
			Sala sala = salaService.findById(salaId).get();
			if(binding.hasErrors()) {
				model.addAttribute("sala", sala);
				Collection<Sesion> sesiones = hs.activeSessions(salaId);
			      model.addAttribute("sesiones", sesiones);
				return "salas/salaDetails";
			}else {
				cs.save(cita,cls.findClienteByUsername(user.getUsername()));
				model.addAttribute("message", "You now have an appointment in " + sala.getName() + " " + cita.getSesion());
				return salasListing(model);
			}
		}
    	
}

	

