package me.rflores.clienteapp.controllers;

import me.rflores.clienteapp.models.entities.Cliente;
import me.rflores.clienteapp.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // List all clients
    @GetMapping
    public String listClientes(Model model) {
        List<Cliente> clientes = clienteService.listarTodos();
        model.addAttribute("clientes", clientes);
        return "clientes"; // Render the "clientes.html" template
    }

    // Show form for adding a new client
    @GetMapping("/nuevo")
    public String showAddClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "add-cliente"; // Render the "add-clientes.html" template
    }

    // Process form to add new client
    @PostMapping("/agregar")
    public String addCliente(@ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-cliente";
        }
        clienteService.guardar(cliente);
        return "redirect:/clientes"; // Redirect to the list of clients after adding
    }

    // Show form for updating an existing client
    @GetMapping("/editar/{id}")
    public String showEditClienteForm(@PathVariable("id") int id, Model model) {
        clienteService.obtenerPorId(id)
                .ifPresent(cliente -> model.addAttribute("cliente", cliente));

        return "edit-cliente"; // Render the "edit-clientes.html" template
    }

    // Process form to update an existing client
    @PostMapping("/actualizar/{id}")
    public String updateCliente(@PathVariable("id") int id, @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-cliente";
        }
        clienteService.guardar(cliente);
        return "redirect:/clientes"; // Redirect to the list of clients after updating
    }

    // Delete a client
    @GetMapping("/eliminar/{id}")
    public String deleteCliente(@PathVariable("id") int id) {
        clienteService.borrar(id);
        return "redirect:/clientes"; // Redirect to the list of clients after deleting
    }
}