package org.springframework.samples.petclinic.web;

import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bono;
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
	
	@Autowired
	BonoService bonoservice;
	
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
			bonoservice.save(bono);
			model.addAttribute("message", "El Bono ha sido creado");			
			return listBonos(model);
		}
	}
}
