package me.rflores.pruebas;

import me.rflores.modelo.entidades.Curso;
import me.rflores.servicios.CursoService;
import me.rflores.servicios.impl.CursoServiceImpl;

public class Prueba02 {
    public static void main(String[] args) {
        var curso = new Curso(200, "Java 2", 10);
        CursoService servicio = new CursoServiceImpl();
        servicio.grabar(curso);
    }
}
