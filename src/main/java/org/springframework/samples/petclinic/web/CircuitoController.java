package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Circuito;
import org.springframework.samples.petclinic.service.CircuitoService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/circuitos")
public class CircuitoController {
	
	public static final String CIRCUITOS_FORM ="/circuitos/createOrUpdateCircuitosForm";
	public static final String CIRCUITOS_LISTING ="/circuitos/CircuitosListing";
	
	private final CircuitoService circuitosServices;
    private final SalaService salasServices;

    @Autowired
    public CircuitoController(CircuitoService circuitosServices, SalaService salasServices) {
    	this.circuitosServices = circuitosServices;
        this.salasServices = salasServices;
    }
	
	@GetMapping
	public String cicuitosListing(ModelMap model) {
		Collection<Circuito> c = circuitosServices.findAll();
		for(Circuito i:c) {
			i.setAforo(circuitosServices.getAforo(i));
		}
		model.addAttribute("circuitos",c);
		return CIRCUITOS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editCircuito(@PathVariable("id") int id,ModelMap model ) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(circuito.isPresent()) {
			Circuito c = circuito.get();
			c.setAforo(circuitosServices.getAforo(c));
			model.addAttribute("circuito",c);
			model.addAttribute("salas", salasServices.findAll());
			return CIRCUITOS_FORM;
		}else {
			model.addAttribute("message","We could not find the circuit you are trying to edit.");
			return CIRCUITOS_LISTING;
		}
	}
	@PostMapping("/{id}/edit")
	public String editCircuito(@PathVariable("id") int id, @Valid Circuito modifiedCircuito, BindingResult binding,ModelMap model) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(binding.hasErrors()) {
			model.put("circuito", modifiedCircuito);
			model.put("salas", salasServices.findAll());
			return CIRCUITOS_FORM;
		}else {
			modifiedCircuito.setAforo(circuitosServices.getAforo(modifiedCircuito));
			BeanUtils.copyProperties(modifiedCircuito, circuito.get(),"id");
			circuitosServices.save(modifiedCircuito);
			model.addAttribute("message", "The circuit was updated successfully.");
			return cicuitosListing(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteCircuito(@PathVariable("id") int id, ModelMap model) {
		Optional<Circuito> circuito = circuitosServices.findById(id);
		if(circuito.isPresent()) {
			circuitosServices.delete(circuito.get());
			model.addAttribute("message", "The circuit was deleted successfully.");
			return cicuitosListing(model);
		}else {
			model.addAttribute("message", "We could not find the circuit you are trying to delete.");
			return cicuitosListing(model);
		}
	}
	
	
	@GetMapping("/new")
	public String editNewCircuito(ModelMap model) {
		model.addAttribute("circuito",new Circuito());
		model.addAttribute("salas", salasServices.findAll());
		return CIRCUITOS_FORM;
	}
	
	@PostMapping("/new")
	public String saveNewCircuito(@Valid Circuito circuito, BindingResult binding,ModelMap model) {
		if(binding.hasErrors()) {
			model.put("circuito", circuito);
			model.put("salas", salasServices.findAll());
			return CIRCUITOS_FORM;
			
		}else {
			circuito.setAforo(circuitosServices.getAforo(circuito));
			circuitosServices.save(circuito);
			model.addAttribute("message", "The circuit was created successfully.");
			return cicuitosListing(model);
			
		}
	}

	
	
	

}