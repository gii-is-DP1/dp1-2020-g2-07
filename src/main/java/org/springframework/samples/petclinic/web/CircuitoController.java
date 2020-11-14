package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.service.CircuitoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/circuitos")
public class CircuitoController {
	
	public static final String CIRCUITOS_FORM ="/circuitos/createOrUpdateCircuitosForm";
	public static final String CIRCUITOS_LISTING ="/circuitos/CircuitosListing";
	
	@Autowired
	CircuitoService circuitosServices;
	
	@GetMapping
	public String cicuitosListing(ModelMap model) {
		model.addAttribute("circuitos",circuitosServices.findAll());
		return CIRCUITOS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editCircuito(@PathVariable("id") int id,ModelMap model ) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(circuito.isPresent()) {
			model.addAttribute("circuito",circuito.get());
			return CIRCUITOS_FORM;
		}else {
			model.addAttribute("message","No pudimos encontrar el circuito que buscas");
			return CIRCUITOS_LISTING;
		}
	}
	@PostMapping("/{id}/edit")
	public String editCircuito(@PathVariable("id") int id,@Valid Circuito modifiedCircuito, BindingResult binding,ModelMap model) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(binding.hasErrors()) {
			return CIRCUITOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedCircuito, circuito.get(),"id");
			model.addAttribute("circuito", modifiedCircuito);
			model.addAttribute("message", "El circuito se actualizó correctamente.");
			return cicuitosListing(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteSala(@PathVariable("id") int id, ModelMap model) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(circuito.isPresent()) {
			circuitosServices.delete(circuito.get());
			model.addAttribute("message", "El circuito se eliminó correctamente.");
			return cicuitosListing(model);
		}else {
			model.addAttribute("message", "No pudimos encontrar el circuito que intentas eliminar.");
			return cicuitosListing(model);
		}
	}
	
	

}
