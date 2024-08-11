package me.rflores.sistemaventas.modelos.daos.impl;

import me.rflores.sistemaventas.modelos.daos.ClienteDao;
import me.rflores.sistemaventas.modelos.entidades.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteDaoMemory implements ClienteDao {
    private static List<Cliente> clientes = Collections.synchronizedList(new ArrayList<>());

    public ClienteDaoMemory() {
    }

    @Override
    public void create(Cliente cliente) {
        if (!existeCliente(cliente)) {
            clientes.add(cliente);
        } else {
            System.out.println("ERROR: Ya existe un Evento con los mismos datos");
        }
    }

    @Override
    public void update(Cliente cliente) {
        var clienteEncontrado = find(cliente.getCodigo());
        if (clienteEncontrado != null) {
            var index = clientes.indexOf(clienteEncontrado);
            clientes.set(index, cliente);
        }
    }

    @Override
    public void delete(Integer id) {
        var cliente = find(id);
        if (cliente != null) {
            clientes.remove(cliente);
        }
    }

    @Override
    public List<Cliente> findAll() {
        return clientes;
    }

    @Override
    public Cliente find(Integer id) {
        return this.clientes.stream().filter(e -> e.getCodigo() == id)
            .findFirst().orElse(null);
    }

    private boolean existeCliente(Cliente cliente) {
        return this.clientes.stream().anyMatch(e -> e.equals(cliente));
    }
}
