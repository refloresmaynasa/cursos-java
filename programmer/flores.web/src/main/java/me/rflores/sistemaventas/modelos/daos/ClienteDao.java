package me.rflores.sistemaventas.modelos.daos;

import me.rflores.sistemaventas.modelos.entidades.Cliente;

import java.util.List;

public interface ClienteDao extends EntidadDao<Cliente, Integer> {
    public List<Cliente> findAllByNombre(String nombre);

    public List<Cliente> findAllByApellido(String apellido);
}
