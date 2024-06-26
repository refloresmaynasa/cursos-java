package me.rflores.pruebas;

import me.rflores.modelo.entidades.Alumno;
import me.rflores.servicios.impl.AlumnoServiceImpl;

public class Ejercicio01 {
    public static void main(String[] args) {
        // TODO mostrar lista de 4 alumnos en memoria usando Service
        var alumno1 = new Alumno(1, "Carlos", "AP", 10, 20);
        var alumno2 = new Alumno(2, "Juan", "RE", 18, 21);
        var alumno3 = new Alumno(3, "Pedro", "RE", 15, 22);
        var alumno4 = new Alumno(4, "Luis", "AP", 20, 19);

        var servicio = new AlumnoServiceImpl();

        servicio.grabar(alumno1);
        servicio.grabar(alumno2);
        servicio.grabar(alumno3);
        servicio.grabar(alumno4);

        System.out.println("Listado de alumnos");

        for (var alumno : servicio.listar())
            System.out.println(alumno);
    }
}
