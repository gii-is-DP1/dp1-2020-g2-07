package org.springframework.samples.petclinic.web;

import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Toallas;
import org.springframework.samples.petclinic.service.ToallasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toallas")
public class ToallasController {

	public static final String TOALLAS_LISTING="toallas/ToallasListing";


	@Autowired
	ToallasService toallasService;

	@GetMapping
	public String listToallas(ModelMap model)
	{
		model.addAttribute("toallas",toallasService.findAll());
		return TOALLAS_LISTING;
	}

	@GetMapping("/{id}/delete")
	public String deleteToalla(@PathVariable("id") int id,ModelMap model) {
		Optional<Toallas> toalla=toallasService.findById(id);
		if(toalla.isPresent()) {
			toallasService.delete(toalla.get());
			model.addAttribute("message","Borrado sin problemas!");
			return listToallas(model);
		}else {
			model.addAttribute("message","No encontrado");
			return listToallas(model);
		}
	}
}