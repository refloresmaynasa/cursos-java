package me.rflores.sistemaventas.utiles;

public class Util {
    private static int secuencia = 3;

    public static int obtenerSecuencia() {
        return ++secuencia;
    }
}
