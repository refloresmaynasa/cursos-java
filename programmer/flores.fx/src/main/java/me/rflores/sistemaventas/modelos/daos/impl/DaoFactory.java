package me.rflores.sistemaventas.modelos.daos.impl;

import me.rflores.sistemaventas.modelos.daos.ClienteDao;
import me.rflores.sistemaventas.utiles.TipoDao;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private DaoFactory() {
    }

    public ClienteDao obtenerClienteDao(TipoDao tipo) {
        switch (tipo) {
            case MEMORY -> { return new ClienteDaoMemory(); }
            case DATABASE -> { return new ClienteDaoDatabase(); }
        }
        return null;
    }
}
