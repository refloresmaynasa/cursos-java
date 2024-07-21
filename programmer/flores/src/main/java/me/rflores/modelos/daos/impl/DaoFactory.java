package me.rflores.modelos.daos.impl;

import me.rflores.modelos.daos.EventoDao;
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
            case MEMORY -> { return new EventoDaoMemory(); }
            case FILE -> { return new EventoDaoFile(); }
            case DATABASE -> { return new EventoDaoDatabase(); }
        }
        return null;
    }
}
