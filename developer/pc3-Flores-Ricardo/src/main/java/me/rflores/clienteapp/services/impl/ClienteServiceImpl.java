package me.rflores.clienteapp.services.impl;

import me.rflores.clienteapp.models.daos.ClienteRepository;
import me.rflores.clienteapp.models.entities.Cliente;
import me.rflores.clienteapp.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return (List<Cliente>)clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void borrar(int id) {
        clienteRepository.deleteById(id);
    }
}
