package me.rflores.clienteapp.services;


import me.rflores.clienteapp.models.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarTodos();

    Optional<Cliente> obtenerPorId(int id);

    Cliente guardar(Cliente cliente);

    void borrar(int id);
}
