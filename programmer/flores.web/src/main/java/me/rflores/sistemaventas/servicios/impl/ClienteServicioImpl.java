package me.rflores.sistemaventas.servicios.impl;

import me.rflores.sistemaventas.modelos.daos.ClienteDao;
import me.rflores.sistemaventas.modelos.daos.impl.DaoFactory;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.utiles.Constantes;

import java.util.List;

public class ClienteServicioImpl implements ClienteServicio {
    private ClienteDao dao;

    public ClienteServicioImpl() {
        this.dao = DaoFactory.getInstance().obtenerClienteDao(Constantes.OPCION_DAO);
    }

    @Override
    public List<Cliente> listar() {
        return dao.findAll();
    }

    @Override
    public List<Cliente> listarPorNombre(String nombre) {
        return dao.findAllByNombre(nombre);
    }

    @Override
    public List<Cliente> listarPorApellido(String apellido) {
        return dao.findAllByApellido(apellido);
    }

    @Override
    public void grabar(Cliente cliente) {
        dao.create(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        dao.update(cliente);
    }


    @Override
    public void eliminar(int id) {
        this.dao.delete(id);
    }

    @Override
    public Cliente buscar(int codigo) {
        return dao.find(codigo);
    }
}
