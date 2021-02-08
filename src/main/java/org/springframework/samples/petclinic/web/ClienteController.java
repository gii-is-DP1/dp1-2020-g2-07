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
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    public static final String CLIENTS_FORM="clientes/createOrUpdateClientsForm";
    public static final String CLIENTS_LISTING="clientes/ClientsListing";

    public Boolean hasAuthority(Optional<Cliente> cliente, User user) {
        if (user.equals(null) || cliente.equals(null))
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
            	return "redirect:/clientes/"+String.valueOf(c.get().getId());
            }else model.addAttribute("clientes", clientService.findAll());

            return CLIENTS_LISTING;
        }
        else return "redirect:/login-error";
    }

    @GetMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, ModelMap model, Authentication auth) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        User user = userService.findUser(auth.getName()).get();
        if(cliente.isPresent()) {
            if(hasAuthority(cliente, user)){
                model.addAttribute("cliente",cliente.get());
                model.addAttribute("suscripcion",cliente.get().getSuscripcion());
                return CLIENTS_FORM;
            }else{
                model.addAttribute("message","Acceso denegado");
            }
        }else{
            model.addAttribute("message","No se encuentra el cliente que pretende editar");
            return CLIENTS_LISTING;
        }
        return listClients(model,auth);
    }

    @PostMapping("/{clientId}/edit")
    public String editCliente(@PathVariable("clientId") int clientId, @Valid Cliente modifiedClient,
    		BindingResult binding, ModelMap model, @RequestParam(value="version", required=false) Integer version, Authentication auth) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        if (cliente.isPresent()){
            if(binding.hasErrors()) {
                log.info(String.format("Client with username %s and ID %d wasn't able to be updated",
                    cliente.get().getUser().getUsername(), cliente.get().getId()));
                return CLIENTS_FORM;
            }else if(cliente.get().getVersion()!=version){
            	model.addAttribute("message", "Concurrent modification of client, try again later");
            	return listClients(model,auth);
            }else {
                boolean enable = userService.findUser(cliente.get().getUser().getUsername()).get().isEnabled();
                modifiedClient.setCategory(cliente.get().getCategory());
                modifiedClient.setVersion(modifiedClient.getVersion()+1); //@Version no se incrementa solo
                BeanUtils.copyProperties(modifiedClient, cliente.get(), "id");
                cliente.get().getUser().setEnabled(enable);
                clientService.save(cliente.get(), "edit");
                model.addAttribute("message","Client modified");
                return CLIENTS_LISTING;
            }
        }else{
            model.addAttribute("message", "That client doesnt exist");
            return listClients(model,auth);
        }
    }

    @GetMapping("/{clientId}/delete")
    public String deleteCliente(@PathVariable("clientId") int clientId,ModelMap model, Authentication auth) {
        Optional<Cliente> cliente =clientService.findById(clientId);
        if(cliente.isPresent()) {
            clientService.delete(cliente.get());
            model.addAttribute("message","Client deleted");
            return CLIENTS_LISTING;
        }else {
            model.addAttribute("message","That client doesnt exist");
            return CLIENTS_LISTING;
        }
    }

    @GetMapping("/new")
    public String editNewClient(ModelMap model) {
        model.addAttribute("cliente",new Cliente());
        model.addAttribute("user", new User());
        return CLIENTS_FORM;
    }

    @PostMapping("/new")
    public String saveNewCliente(@Valid Cliente cliente, BindingResult binding,ModelMap model,Authentication auth) {
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
            cliente.setCategory(Categoria.CLIENTE);
            clientService.save(cliente, "new");
            model.addAttribute("message", "The client was created successfully!");
            return listClients(model,auth);
        }
    }

    @GetMapping("/{clientId}")
    public String showClient(@PathVariable("clientId") int clientId, Authentication auth, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(clientId);
        if(cliente.isPresent()){
            if (hasAuthority(cliente, userService.findUser(auth.getName()).get())) {
                model.addAttribute("cliente", cliente.get());
                return "clientes/clienteDetails";
            } else {
                model.addAttribute("message", "You aren't allowed");
                return listClients(model,auth);
            }
        }else{
            model.addAttribute("message","That employee doesn't exist");
            return listClients(model,auth);
        }
    }

    @GetMapping("/{clientId}/newPay")
    public String addSalary(@PathVariable("clientId") int clientId, ModelMap model, Authentication auth) {
        Optional<Cliente> c = clientService.findById(clientId);
        if(!c.isPresent()){
            model.addAttribute("message","That client doesnt exists");
            return listClients(model,auth);
        }else{
            if (hasAuthority(c, userService.findUser(auth.getName()).get())) {
                model.addAttribute("pago",new Pago());
                model.addAttribute("cliente",clientService.findById(clientId).get());
                return "pay/payForm";
            } else {
                model.addAttribute("message", "You aren't allowed");
                return listClients(model,auth);
            }
        }
    }

    @PostMapping("/{clientId}/newPay")
    public String saveSalary(@PathVariable("clientId") int clientId,@Valid @ModelAttribute("pago") Pago pago, BindingResult binding, ModelMap model){
        Cliente cliente = clientService.findById(clientId).get();
        model.addAttribute("cliente", cliente);

        if (cliente.getPagos().stream()
            .anyMatch(p -> p.getfEmision().getMonth().equals(pago.getfEmision().getMonth())
            && p.getfEmision().getYear() == pago.getfEmision().getYear())) {
            model.addAttribute("message", "There is already a payment for that month");
            return "pay/payForm";
        }
        else if(binding.hasErrors()){
            log.info(String.format("Pay of client with username %s and ID %d wasn't able to be created",
                cliente.getUser().getUsername(), cliente.getId()));
            return "pay/payForm";
        }else{
            pago.setCliente(cliente);
            clientService.addPayToClient(clientId, pago);
            return "redirect:/clientes/" + String.valueOf(clientId);
        }
    }

}
