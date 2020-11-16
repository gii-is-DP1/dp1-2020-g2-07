package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Sala;
import org.springframework.samples.petclinic.service.SalaService;
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
	
	@Autowired
	SalaService salasServices;
	
	@GetMapping
	public String salasListing(ModelMap model) {
		model.addAttribute("salas", salasServices.findAll());
		return SALAS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editSala(@PathVariable("id") int id,ModelMap model) {
		Optional<Sala> sala = salasServices.findById(id);
		if(sala.isPresent()) {
			model.addAttribute("sala", sala.get());
			return SALAS_FORM;
		}else {
			model.addAttribute("message", "¡No pudimos encontrar la sala que buscas!");
			return SALAS_LISTING;
		}
	}
	
	
	@PostMapping("/{id}/edit")
	public String editSala(@PathVariable("id") int id,@Valid Sala modifiedSala, BindingResult binding,ModelMap model) {
		Optional<Sala> sala = salasServices.findById(id);
		if(binding.hasErrors()) {
			return SALAS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedSala, sala.get(),"id");
			model.addAttribute("sala", modifiedSala);
			model.addAttribute("message", "La sala se actualizó correctamente.");
			return salasListing(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteSala(@PathVariable("id") int id, ModelMap model) {
		Optional<Sala> sala = salasServices.findById(id);
		if(sala.isPresent()) {
			salasServices.delete(sala.get());
			model.addAttribute("message", "La sala se eliminó correctamente.");
			return salasListing(model);
		}else {
			model.addAttribute("message", "No pudimos encontrar la sala que intentas eliminar.");
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
			this.salasServices.save(sala);
			model.addAttribute("message", "La sala se añadió correctamente.");
			return salasListing(model);
		}
	}
	
	
	
}
