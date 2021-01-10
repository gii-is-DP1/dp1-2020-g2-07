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

@Controller
@RequestMapping("/employees/{employeeId}")
public class HorarioController {
	
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
	
    @GetMapping("/newTimeTable")
    public String addTimeTable(Employee employee, ModelMap model) {
    	Horario h = new Horario();
    	employee.addHorario(h);
        model.addAttribute("horario",h);
        return "timetable/horarioForm";
    }

    @PostMapping("/newTimeTable")
    public String saveTimeTable(Employee e,@Valid @ModelAttribute("horario") Horario horario, BindingResult binding, ModelMap model){
        if(binding.hasErrors()){
            return "timetable/horarioForm";
        }else if(LocalDate.now().isAfter(horario.getFecha())) {
        	model.addAttribute("message", "The day you picked can't be in the past");
        	return "timetable/horarioForm";
        }else if(horarioService.dayAlreadyInSchedule(e, horario)){
        	model.addAttribute("message", "The day you picked already exist in the employee schedule");
        	return "timetable/horarioForm";	
        }else{
            horarioService.save(horario);

            return "redirect:/employees/" + String.valueOf(e.getId());
        }
    }
    
    @GetMapping("/TimeTable/{horarioId}")
    public ModelAndView showEmployeeTimeTable(@PathVariable("horarioId") int horarioId) {
        ModelAndView mav = new ModelAndView("employees/employeeTimeTable");
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
	
    @GetMapping("/TimeTable/{horarioId}/newSesion")
    public String addSession(ModelMap model,@PathVariable("horarioId") int horarioId) {
        model.addAttribute("horarioID", horarioId);
        model.addAttribute("sesion", this.horarioService.findSesionesHorario(horarioId));
        model.addAttribute("newSesion", new Sesion());
        model.addAttribute("salas",salaService.findAll());
        model.addAttribute("hours_op",horarioService.SesionHours(LocalTime.parse("09:00")));
        model.addAttribute("hours_end",horarioService.SesionHours(LocalTime.parse("10:00")));
        return "timetable/sesionForm";
    }
    
    @PostMapping("/TimeTable/{horarioId}/newSesion")
    public String saveTimeTable(Employee e, @PathVariable("horarioId") int horarioId,@Valid @ModelAttribute("newSesion") Sesion sesion, BindingResult binding, ModelMap model){ 	   	
    	if(binding.hasErrors()||!sesion.validate()||horarioService.checkDuplicatedSessions(sesion, horarioId) || !e.validEmployee(e.getProfession().toString(), sesion.getSala().getRoom_type().toString())){
        	model.addAttribute("horarioID", horarioId);
            model.addAttribute("salas",salaService.findAll());
            model.addAttribute("sesion", this.horarioService.findSesionesHorario(horarioId));
            model.addAttribute("hours_op",horarioService.SesionHours(LocalTime.parse("09:00")));
            model.addAttribute("hours_end",horarioService.SesionHours(LocalTime.parse("10:00")));
            if(!sesion.validate()) {
            	model.addAttribute("message", "Start time must be before end time");
            }
            if(horarioService.checkDuplicatedSessions(sesion, horarioId)) {
            	model.addAttribute("message", "This room is already in use at this time");
            }
            if(!e.validEmployee(e.getProfession().toString(), sesion.getSala().getRoom_type().toString())) {
            	model.addAttribute("message", "The employee is not qualified to work in this room");
            }
        	

            return "timetable/sesionForm";
        }else{
        	sesion.setHorario(horarioService.findById(horarioId).get());
            horarioService.addSesion(horarioId, sesion);

            return "redirect:/employees/" + String.valueOf(e.getId());
        }
    }
    
    

}
