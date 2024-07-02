package daos;

import daos.impl.EventoDaoImplDataBase;
import daos.impl.EventoDaoImplFile;
import daos.impl.EventoDaoImplMemory;

public class DaoFactory {
    public static EventoDao getEventoDao(utiles.Tipo tipo) {
        switch (tipo) {
            case MEMORIA -> {
                return new EventoDaoImplMemory();
            }
            case ARCHIVO -> {
                return new EventoDaoImplFile();
            }
            case BASE -> {
                return new EventoDaoImplDataBase();
            }
        }
        return null;
    }
}
