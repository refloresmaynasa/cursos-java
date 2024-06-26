package me.rflores.pruebas;

import me.rflores.modelo.entidades.Curso;
import me.rflores.servicios.CursoService;
import me.rflores.servicios.impl.CursoServiceImpl;

public class Prueba02 {
    public static void main(String[] args) {
        var curso1 = new Curso(100, "Java 1", 10);
        var curso2 = new Curso(200, "Java 2", 10);
        var curso3 = new Curso(300, "Java 3", 10);

        CursoService servicio = new CursoServiceImpl();

        servicio.grabar(curso1);
        servicio.grabar(curso2);
        servicio.grabar(curso3);

        System.out.println("Listado de cursos");

        for (var curso : servicio.listar())
            System.out.println(curso);
    }
}
