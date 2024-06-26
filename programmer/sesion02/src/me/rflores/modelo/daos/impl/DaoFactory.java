package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.daos.CursoDao;
import me.rflores.util.Tipo;

public class DaoFactory {
    private static final DaoFactory INSTANCE = new DaoFactory();

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    private DaoFactory() {
    }

    public CursoDao getCursoDao(Tipo tipo) {
        switch (tipo) {
            case DB -> {
                return new CursoDaoDataBase();
            }
            case FILE -> {
                return new CursoDaoFile();
            }
            case XML -> {
                return new CursoDaoXml();
            }
            case MEMORY -> {
                return new CursoDaoMemory();
            }
        }
        return null;
    }

    public AlumnoDao getAlumnoDao(Tipo tipo) {
        switch (tipo) {
            case DB -> {
                return new AlumnoDaoDataBase();
            }
            case FILE -> {
                return new AlumnoDaoFile();
            }
            case XML -> {
                return new AlumnoDaoXml();
            }
            case MEMORY -> {
                return new AlumnoDaoMemory();
            }
        }
        return null;
    }
}
