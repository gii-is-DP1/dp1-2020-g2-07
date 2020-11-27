package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaController {

	private static final String CITAS_LISTING = "/citasListing";
	
	@Autowired
	CitaService citas;
	
	@GetMapping
	public String getCitas(ModelMap model) {
		model.addAttribute("citas", citas.findAll());
		return CITAS_LISTING;
	}
}
