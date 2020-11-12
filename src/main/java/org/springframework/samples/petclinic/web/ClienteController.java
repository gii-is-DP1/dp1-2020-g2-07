package org.springframework.samples.petclinic.web;

import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.SubType;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.Encoder;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    public static final String CLIENTS_FORM="clientes/createOrUpdateClientsForm";
    public static final String CLIENTS_LISTING="clientes/ClientsListing";

    @Autowired
    ClienteService clientService;

    @GetMapping
    public String listClients(ModelMap model){
        model.addAttribute("clientes", clientService.findAll());
        return CLIENTS_LISTING;
    }

    @GetMapping("/{id}/edit")
    public String editCliente(@PathVariable("id") int id, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(id);
        if(cliente.isPresent()) {
            model.addAttribute("cliente",cliente.get());
            return CLIENTS_FORM;
        }else {
            model.addAttribute("message","No se encuentra el cliente que pretende editar");
            return listClients(model);
        }
    }

    @PostMapping("/{id}/edit")
    public String editCliente(@PathVariable("id") int id, @Valid Cliente modifiedClient, BindingResult binding, ModelMap model) {
        Optional<Cliente> cliente = clientService.findById(id);
        if(binding.hasErrors()) {
            return CLIENTS_FORM;
        }else {
            BeanUtils.copyProperties(modifiedClient, cliente.get(), "{id,categoria}");
            clientService.save(cliente.get());
            model.addAttribute("message","Se ha modificado el cliente");
            return listClients(model);
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCliente(@PathVariable("id") int id,ModelMap model) {
        Optional<Cliente> cliente =clientService.findById(id);
        if(cliente.isPresent()) {
            clientService.delete(cliente.get());
            model.addAttribute("message","Se ha borrado satisfactoriamente");
            return listClients(model);
        }else {
            model.addAttribute("message","No se encuentra ese cliente");
            return listClients(model);
        }
    }

}
