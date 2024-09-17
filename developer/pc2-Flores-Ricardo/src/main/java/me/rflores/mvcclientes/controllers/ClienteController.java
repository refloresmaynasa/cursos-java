package me.rflores.mvcclientes.controllers;

import jakarta.validation.Valid;
import me.rflores.mvcclientes.models.entities.Cliente;
import me.rflores.mvcclientes.services.ClienteService;
import me.rflores.mvcclientes.services.impl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String getAllClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "cliente/list";
    }

    @GetMapping("/nuevo")
    public String showNewClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    @PostMapping
    public String saveCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cliente/form"; // Show form again with error messages
        }
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String showEditClienteForm(@PathVariable int id, Model model) {
        clienteService.obtenerPorId(id).ifPresent(cliente -> model.addAttribute("cliente", cliente));
        return "cliente/form";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteCliente(@PathVariable int id) {
        clienteService.borrar(id);
        return "redirect:/clientes";
    }
}
