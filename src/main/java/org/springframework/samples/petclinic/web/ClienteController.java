package org.springframework.samples.petclinic.web;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
	public static final String CREAR_O_MODIFICAR_CLIENTE_FORM="clientes/crearOModificarCliente";
	
    @Autowired
    ClienteService clientService;

    @GetMapping(value = "/clientes")
    public String listClients(ModelMap model){
        model.addAttribute("clientes", clientService.findAll());
        return "clientes/listarClientes";
    }
   
   @GetMapping("/clientes/{clienteId}")
	public ModelAndView showCliente(@PathVariable("clienteId") int clienteId) {
		ModelAndView mav = new ModelAndView("clientes/mostrarCliente");
		mav.addObject(this.clientService.findClienteById(clienteId));
		return mav;
	}
   
   @GetMapping("/clientes/{clienteId}/edit")
	public String editCliente(@PathVariable("clienteId") int clienteId,ModelMap model) {
		Optional<Cliente> cliente=Optional.of(clientService.findClienteById(clienteId));
		if(cliente.isPresent()) {
			model.addAttribute("cliente",cliente.get());
			return CREAR_O_MODIFICAR_CLIENTE_FORM;
		}else {
			model.addAttribute("message","We cannot find the disease you tried to edit!");
			return "redirect:/clientes";
		}
	}

   @PostMapping("/clientes/{clienteId}/edit")
	public String editCliente(@PathVariable("clienteId") int clienteId, @Valid Cliente clienteMod, BindingResult binding, ModelMap model) {
		Optional<Cliente> cliente=Optional.of(clientService.findClienteById(clienteId));
		if(binding.hasErrors()) {			
			return CREAR_O_MODIFICAR_CLIENTE_FORM;
		}else {
			BeanUtils.copyProperties(clienteMod, cliente.get(), "clienteId");
			clientService.save(cliente.get());
			model.addAttribute("message","Cliente actualizado con exito!");
			return "redirect:/clientes";
		}
	}
}
