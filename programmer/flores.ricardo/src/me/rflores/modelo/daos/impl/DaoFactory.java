package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.EventoDao;
import me.rflores.utiles.TipoDao;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private DaoFactory() {
    }

    public EventoDao obtenerEventoDao(TipoDao tipo) {
        switch (tipo) {
            case MEMORIA -> new EventoDaoMemoria();
        }
        return null;
    }
}
