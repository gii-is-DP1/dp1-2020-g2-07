package org.springframework.samples.petclinic.web;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    public static final String CLIENTS_FORM="clientes/createOrUpdateClientsForm";
    public static final String CLIENTS_LISTING="clientes/ClientsListing";

    @Autowired
    ClienteService clientService;

    @Autowired
    UserService userService;

    @GetMapping
    public String listClients(ModelMap model){
        model.addAttribute("clientes", clientService.findAll());
        return CLIENTS_LISTING;
    }

    @GetMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        if(cliente.isPresent()) {
            model.addAttribute("cliente",cliente.get());
            return CLIENTS_FORM;
        }else {
            model.addAttribute("message","No se encuentra el cliente que pretende editar");
            return listClients(model);
        }
    }

    @PostMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, @Valid Cliente modifiedClient, BindingResult binding, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        if(binding.hasErrors()) {
            return CLIENTS_FORM;
        }else {
            modifiedClient.setCategory(cliente.get().getCategory());
            BeanUtils.copyProperties(modifiedClient, cliente.get(), "id");
            clientService.save(cliente.get(), "edit");
            model.addAttribute("message","Se ha modificado el cliente");
            return listClients(model);
        }
    }

    @GetMapping("/{clientId}/delete")
    public String deleteCliente(@PathVariable("clientId") int clientId,ModelMap model) {
        Optional<Cliente> cliente =clientService.findById(clientId);
        if(cliente.isPresent()) {
            clientService.delete(cliente.get());
            model.addAttribute("message","Se ha borrado satisfactoriamente");
            return listClients(model);
        }else {
            model.addAttribute("message","No se encuentra ese cliente");
            return listClients(model);
        }
    }

    @GetMapping("/new")
    public String editNewClient(ModelMap model) {
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("user", new User());
        return CLIENTS_FORM;
    }

    @PostMapping("/new")
    public String saveNewEmployee(@Valid Cliente cliente, BindingResult binding,ModelMap model) {
        if(binding.hasErrors()) {
            return CLIENTS_FORM;
        }else {
            cliente.setCategory(Categoria.EMPLEADO);
            clientService.save(cliente, "new");
            model.addAttribute("message", "The client was created successfully!");
            return listClients(model);
        }
    }

    @GetMapping("/{clientId}")
    public ModelAndView showEmployee(@PathVariable("clientId") int clientId) {
        ModelAndView mav = new ModelAndView("clientes/clienteDetails");
        mav.addObject("cliente",this.clientService.findById(clientId).get());
        return mav;
    }

    @GetMapping("/{clientId}/newPay")
    public String addSalary(@PathVariable("clientId") int clientId, ModelMap model) {
        model.addAttribute("cliente",clientService.findById(clientId).get());
        model.addAttribute("pago",new Pago());
        return "pay/payForm";
    }

    @PostMapping("/{clientId}/newPay")
    public String saveSalary(@PathVariable("clientId") int clientId,@Valid @ModelAttribute("pago") Pago pago, BindingResult binding, ModelMap model){
        model.addAttribute("cliente",clientService.findById(clientId).get());
        if(binding.hasErrors()){
            model.addAttribute("message", "hay un error capo");
            return "pay/payForm";
        }else{
            pago.setCliente(clientService.findById(clientId).get());
            clientService.addPayToClient(clientId, pago);
            return "redirect:/clientes/" + String.valueOf(clientId);
        }
    }
}
