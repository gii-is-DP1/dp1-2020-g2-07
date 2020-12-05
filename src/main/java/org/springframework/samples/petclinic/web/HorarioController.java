package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
	public static final String HORARIOS_FORM ="/horarios/CreateOrUpdateHorariosForm";
	public static final String HORARIOS_LISTING ="/horarios/HorariosListing";
	
	@Autowired 
	HorarioService horariosService;
	
	
	@GetMapping
	public String horariosListing(ModelMap model) {
		model.addAttribute("horarios", horariosService.findAll());
		return HORARIOS_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editHorario(@PathVariable("id") int id,ModelMap model) {
		Optional<Horario> horario = horariosService.findById(id);
		if(horario.isPresent()) {
			model.addAttribute("horario", horario.get());
			return HORARIOS_FORM;
		}else {
			model.addAttribute("message", "We could not find the room you are trying to edit.");
			return HORARIOS_LISTING;
		}
	}
	@PostMapping("/{id}/edit")
	public String editHorario(@PathVariable("id") int id,@Valid Horario modifiedHorario, BindingResult binding,ModelMap model) {
		Optional<Horario> horario = horariosService.findById(id);
		if(binding.hasErrors()) {
			return HORARIOS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedHorario, horario.get(),"id");
			this.horariosService.save(modifiedHorario);
			model.addAttribute("message", "Timetable was updated successfully.");
			return horariosListing(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteHorario(@PathVariable("id") int id, ModelMap model) {
		Optional<Horario> horario = horariosService.findById(id);
		if(horario.isPresent()) {
			horariosService.delete(horario.get());
			model.addAttribute("message", "Timetable  delete successfully.");
			return horariosListing(model);
		}else {
			model.addAttribute("message", "We could not find the timetable you are trying to delete.");
			return horariosListing(model);
		}
	}
	
	@GetMapping(value = "/new")
	public String initCreationForm(ModelMap model) {
		Horario horario = new Horario();
		model.addAttribute("horario", horario);
		return HORARIOS_FORM;
	}

	@PostMapping(value = "/new")
	public String processCreationForm(@Valid Horario horario, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return HORARIOS_FORM;
		}
		else {
			this.horariosService.save(horario);
			model.addAttribute("message", "Timetable create successfully.");
			return horariosListing(model);
		}
	}
	
	
}
