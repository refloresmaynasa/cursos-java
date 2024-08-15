package me.rflores.sistemaventas.modelos.daos.impl;

import me.rflores.sistemaventas.modelos.daos.ClienteDao;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.utiles.Util;

import java.util.ArrayList;
import java.util.List;

public class ClienteDaoMemory implements ClienteDao {
    private static List<Cliente> clientes = new ArrayList<>(List.of(
            new Cliente(1, "Juan", "Pérez", "123456789", "juan@example.com", 1500D),
            new Cliente(2, "María", "González", "987654321", "maria@example.com", 2500D),
            new Cliente(3, "Carlos", "Rodríguez", "555123456", "carlos@example.com", 4000D)));

    public ClienteDaoMemory() {
    }

    @Override
    public void create(Cliente cliente) {
        if (!existeCliente(cliente)) {
            cliente.setCodigo(Util.obtenerSecuencia());
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
        return clientes.stream().filter(e -> e.getCodigo() == id)
                .findFirst().orElse(null);
    }

    private boolean existeCliente(Cliente cliente) {
        return this.clientes.stream().anyMatch(e -> e.equals(cliente));
    }

    @Override
    public List<Cliente> findAllByNombre(String nombre) {
        return clientes.stream().filter(c -> c.getNombre().startsWith(nombre))
                .toList();
    }

    @Override
    public List<Cliente> findAllByApellido(String apellido) {
        return clientes.stream().filter(c -> c.getApellido().startsWith(apellido))
                .toList();
    }
}
