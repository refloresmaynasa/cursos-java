package org.rflores.pruebas;

import org.rflores.beans.Alumno;

public class Prueba02 {
    public static void main(String[] args) {
        Alumno a1 = new Alumno();
        a1.setNombre("juan");
        a1.setPromedio(15);
        Alumno a2 = new Alumno();
        a2.setNombre("luis");
        a2.setPromedio(12);
        System.out.println(a1.getNombre() + " " + a1.getId() + ":" + a1.getEstado());
        System.out.println(a2.getNombre() + " " + a2.getId() + ":" + a2.getEstado());
    }
}
