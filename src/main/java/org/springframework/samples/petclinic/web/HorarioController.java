package org.springframework.samples.petclinic.web;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Employee;
import org.springframework.samples.petclinic.model.Horario;
import org.springframework.samples.petclinic.model.Sesion;
import org.springframework.samples.petclinic.service.EmployeeService;
import org.springframework.samples.petclinic.service.HorarioService;
import org.springframework.samples.petclinic.service.SalaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/employees/{employeeId}")
public class HorarioController {
	
	public static final String HORARIO_FORM ="schedule/horarioForm";
	
	EmployeeService employeeService;
	HorarioService horarioService;
	SalaService salaService;
	
	@Autowired
	public HorarioController(EmployeeService employeeService, HorarioService horarioService,SalaService salaService) {
		this.employeeService = employeeService;
		this.horarioService = horarioService;
		this.salaService = salaService;
	}
	
	@ModelAttribute("employee")
	public Employee findEmployee(@PathVariable("employeeId") int employeeId) {
		return this.employeeService.findById(employeeId).get();
	}
	
    @GetMapping(value = "/newSchedule")
    public String addDaySchedule(Employee employee, ModelMap model) {
    	Horario h = new Horario();
    	employee.addHorario(h);
        model.addAttribute("horario",h);
        return HORARIO_FORM;
    }

    @PostMapping("/newSchedule")
    public String saveDaySchedule(Employee e,@Valid @ModelAttribute("horario") Horario horario, BindingResult binding, ModelMap model){
        if(binding.hasErrors()){
            return HORARIO_FORM;
        }else if(LocalDate.now().isAfter(horario.getFecha())) {
        	model.addAttribute("message", "The day you picked can't be in the past");
        	return HORARIO_FORM;
        }else if(horarioService.dayAlreadyInSchedule(horario)){
        	log.warn("Day already created");
        	model.addAttribute("message", "The day you picked already exist in the employee schedule");
        	return HORARIO_FORM;	
        }else{
        	horarioService.save(horario);
        	log.info("Day " + horario.getFecha() + " for the employee " + e.getFirst_name() + " has been created");
            return "redirect:/employees/" + String.valueOf(e.getId());
        }
    }
    
    @GetMapping("/schedule/{horarioId}")
    public ModelAndView showEmployeeSchedule(@PathVariable("horarioId") int horarioId) {
        ModelAndView mav = new ModelAndView("employees/employeeSchedule");
        mav.addObject("sesion",this.horarioService.findSesionesHorario(horarioId));
        mav.addObject("horario", this.horarioService.findById(horarioId).get());
        return mav;
    }
    
	@GetMapping("/pastSessions")
	public ModelAndView showPastSessions(@PathVariable("employeeId") int employeeId) {
		ModelAndView mav = new ModelAndView("employees/employeePastSessions");
		mav.addObject("past", horarioService.calcDays(employeeId,"past"));
		return mav;
		
	}
	
    @GetMapping("/schedule/{horarioId}/newSesion")
    public String addSession(ModelMap model,@PathVariable("horarioId") int horarioId) {
        model.addAttribute("horario", horarioId);
        model.addAttribute("sesion", this.horarioService.findSesionesHorario(horarioId));     
        model.addAttribute("sala",salaService.findAll());
        model.addAttribute("horaInicio",horarioService.SesionHours(LocalTime.parse("09:00")));
        model.addAttribute("horaFin",horarioService.SesionHours(LocalTime.parse("10:00")));
        if(!model.containsKey("newSesion")) {
        	model.addAttribute("newSesion", new Sesion());
        }
        return "schedule/sesionForm";
    }
    
    @PostMapping("/schedule/{horarioId}/newSesion")
    public String saveNewSession(Employee e, @PathVariable("horarioId") int horarioId,@Valid @ModelAttribute("newSesion") Sesion sesion, BindingResult binding, ModelMap model){ 	   	
    	if(binding.hasErrors()||!sesion.validate()||horarioService.checkDuplicatedSessions(sesion) || !e.validEmployee(e.getProfession().toString(), sesion.getSala().getRoom_type().toString())){
            if(!sesion.validate()) {
            	model.addAttribute("message", "Start time must be before end time");
            }
            if(horarioService.checkDuplicatedSessions(sesion)) {
            	log.warn("Somebody is working in the room now, try some new hours");
            	model.addAttribute("message", "This room is already in use at this time");
            }
            if(!e.validEmployee(e.getProfession().toString(), sesion.getSala().getRoom_type().toString())) {
            	log.warn("Employee profession: " + e.getProfession().toString() + " does not let him work as a " + sesion.getSala().getRoom_type().toString());
            	model.addAttribute("message", "The employee is not qualified to work in this room");
            }
            return addSession(model,horarioId);
        }else{
            horarioService.addSesion(horarioId, sesion);
            log.info("New session created for " + e.getFirst_name() + " in the " + sesion.getSala());
            return "redirect:/employees/" + String.valueOf(e.getId());
        }
    }
    
    

}
