package me.rflores.clientesapp.utils;

public class Constantes {
    /** Opcion de tipo de DAO a utilizar */
    public static final TipoDao OPCION_DAO = TipoDao.valueOf(Config.getProperty("opcion_persistencia"));
}
