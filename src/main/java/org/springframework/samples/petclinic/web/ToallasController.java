package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ToallasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toallas")
public class ToallasController {

	@Autowired
	ToallasService toallasService;

	@GetMapping
	public String listToallas(ModelMap model)
	{
		model.addAttribute("toallas",toallasService.findAll());
		return "toallas/ToallasListing";
	}
}
