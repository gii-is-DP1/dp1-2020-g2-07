package org.springframework.samples.petclinic.web;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.samples.petclinic.model.Categoria;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    public static final String CLIENTS_FORM="clientes/createOrUpdateClientsForm";
    public static final String CLIENTS_LISTING="clientes/ClientsListing";

    public Boolean hasAuthority(Optional<Cliente> cliente, User user) {
        if (user.equals(null))
            return false;
        else
            return cliente.get().getUser().equals(user) || user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"));
    }

    @Autowired
    ClienteService clientService;

    @Autowired
    UserService userService;

    @ModelAttribute("subTypes")
    public List<SubType> getSubType(){
        return Arrays.stream(SubType.class.getEnumConstants()).collect(Collectors.toList());
    }

    @GetMapping
    public String listClients(ModelMap model, Authentication auth){
        if (auth.isAuthenticated()) {
            Optional<Cliente> c = clientService.clientByUsername(auth.getName());
            if(c.isPresent()) {
            	model.addAttribute("c", c.get());
            }else model.addAttribute("clientes", clientService.findAll());

            return CLIENTS_LISTING;
        }
        else return "/login";
    }

    public String listClients(ModelMap model){
        model.addAttribute("clientes", clientService.findAll());
        return CLIENTS_LISTING;
    }

    @GetMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, ModelMap model, Authentication auth) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        User user = userService.findUser(auth.getName()).get();
        if(cliente.isPresent() && hasAuthority(cliente, user)) {
            model.addAttribute("cliente",cliente.get());
            model.addAttribute("suscripcion",cliente.get().getSuscripcion());
            return CLIENTS_FORM;
        }
        else if (!hasAuthority(cliente, user))
            model.addAttribute("message","Acceso denegado");
        else
            model.addAttribute("message","No se encuentra el cliente que pretende editar");
        return listClients(model);
    }

    @PostMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, @Valid Cliente modifiedClient, BindingResult binding, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        modifiedClient.setCategory(cliente.get().getCategory());
        BeanUtils.copyProperties(modifiedClient, cliente.get(), "id");
        clientService.save(cliente.get(), "edit");
        model.addAttribute("message","Se ha modificado el cliente");
        return listClients(model);
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
    public String saveNewCliente(@Valid Cliente cliente, BindingResult binding,ModelMap model) {
        if(binding.hasErrors()) {
            return CLIENTS_FORM;
        } else {
            Map<Boolean, List<String>> m = userService.checkUser(cliente.getUser());

            if(m.containsKey(false)){
                List<String> ls = m.get(false);
                for (int i = 0; i < ls.size(); i++){
                    model.addAttribute("message", ls);
                }
                return CLIENTS_FORM;
            }

            cliente.setCategory(Categoria.EMPLEADO);
            clientService.save(cliente, "new");
            model.addAttribute("message", "The client was created successfully!");
            return listClients(model);
        }
    }

    @GetMapping("/{clientId}")
    public ModelAndView showClient(@PathVariable("clientId") int clientId, Authentication auth) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        if (hasAuthority(cliente, userService.findUser(auth.getName()).get())) {
            ModelAndView mav = new ModelAndView("clientes/clienteDetails");
            mav.addObject("cliente",this.clientService.findById(clientId).get());
            return mav;
        }
        else {
            return new ModelAndView("/login");
        }
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
