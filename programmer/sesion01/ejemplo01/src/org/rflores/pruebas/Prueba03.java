package org.rflores.pruebas;

import org.rflores.beans.Profesor;

public class Prueba03 {
    public static void main(String[] args) {
        Profesor profesor1 = new Profesor("B", "Juan", true);
        System.out.println(profesor1.getNombre() + "(categoria-" + profesor1.getCategoria() + ")" + ":" + profesor1.getSueldo());

        Profesor profesor2 = new Profesor("a", "Carlos", true);
        System.out.println(profesor2.getNombre() + "(categoria-" + profesor2.getCategoria() + ")" + ":" + profesor2.getSueldo());
    }
}
