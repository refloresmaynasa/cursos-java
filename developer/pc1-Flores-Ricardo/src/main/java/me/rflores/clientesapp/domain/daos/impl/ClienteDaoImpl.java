package me.rflores.clientesapp.domain.daos.impl;

import me.rflores.clientesapp.domain.daos.ClienteDao;
import me.rflores.clientesapp.domain.entities.Cliente;

public class ClienteDaoImpl implements ClienteDao {
    @Override
    public Class<Cliente> getEntityClass() {
        return Cliente.class;
    }
}
