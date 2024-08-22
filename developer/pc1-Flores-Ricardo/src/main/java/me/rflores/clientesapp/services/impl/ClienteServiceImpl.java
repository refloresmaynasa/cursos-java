package me.rflores.clientesapp.services.impl;

import me.rflores.clientesapp.domain.daos.ClientePc1Dao;
import me.rflores.clientesapp.domain.daos.impl.ClientePc1DaoImpl;
import me.rflores.clientesapp.domain.entities.Cliente;
import me.rflores.clientesapp.services.ClienteService;
import me.rflores.clientesapp.utils.Filtro;

import java.util.List;

public class ClienteServiceImpl implements ClienteService {
    private ClientePc1Dao dao;

    public ClienteServiceImpl() {
        this.dao = new ClientePc1DaoImpl();
    }

    @Override
    public List<Cliente> listar() {
        return dao.findAll();
    }

    @Override
    public List<Cliente> listarPorFiltro(String valor, Filtro filtro) {
        List<Cliente> clientes = null;

        switch (filtro) {
            case ByCodigo -> {
                var codigo = Integer.parseInt(valor);
                clientes = List.of(dao.getById(codigo));
            }
            case ByNombre -> {
                clientes=dao.findByNombre(valor);
            }
            case ByApellido -> {
                clientes = dao.findByApellido(valor);
            }
        }

        return clientes;
    }

    @Override
    public void grabar(Cliente cliente) {
        dao.create(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        var clienteDb = dao.getById(cliente.getCodigo());
        if (clienteDb != null) {
            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setApellido(cliente.getApellido());
            clienteDb.setTelefono(cliente.getTelefono());
            clienteDb.setEmail(cliente.getEmail());
            clienteDb.setTotalCompras(cliente.getTotalCompras());
            dao.update(clienteDb);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }


    @Override
    public void eliminar(int id) {
        var cliente = dao.getById(id);
        if (cliente != null) {
            this.dao.delete(cliente);
        }
    }

    @Override
    public Cliente buscar(int codigo) {
        return dao.getById(codigo);
    }

    @Override
    public List<Cliente> listarPorMayoresCompras(double compraMinima) {
        return dao.findByTotalComprasGreaterThan(compraMinima);
    }
}
