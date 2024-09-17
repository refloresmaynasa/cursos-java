package me.rflores.mvcclientes.controllers;

import me.rflores.mvcclientes.models.entities.Cliente;
import me.rflores.mvcclientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String getAllClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "cliente/list";
    }

    @GetMapping("/nuevo")
    public String showNewClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    @PostMapping
    public String saveCliente(@ModelAttribute Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String showEditClienteForm(@PathVariable int id, Model model) {
        clienteService.getClienteById(id).ifPresent(cliente -> model.addAttribute("cliente", cliente));
        return "cliente/form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCliente(@PathVariable int id) {
        clienteService.deleteCliente(id);
        return "redirect:/clientes";
    }
}
