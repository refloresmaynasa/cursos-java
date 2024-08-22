package me.rflores.clientesapp.domain.daos;

import me.rflores.clientesapp.domain.entities.Cliente;

import java.util.List;

public interface ClientePc1Dao extends EntityDao<Cliente, Integer> {
    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByApellido(String apellido);

    List<Cliente> findByTotalComprasGreaterThan(double totalCompras);
}