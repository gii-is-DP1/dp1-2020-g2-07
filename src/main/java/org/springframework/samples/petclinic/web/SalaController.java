package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/salas")
public class SalaController {

	public static final String SALAS_LISTING ="/salas/SalasListing";
	
	@Autowired
	SalaService salasServices;
	
	@GetMapping
	public String salasListing(ModelMap model) {
		model.addAttribute("salas", salasServices.findAll());
		return SALAS_LISTING;
	}
}
