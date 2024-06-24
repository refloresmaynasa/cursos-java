package me.rflores.pruebas;

import me.rflores.modelo.daos.impl.DaoFactory;
import me.rflores.modelo.entidades.Curso;
import me.rflores.util.Util;

public class Prueba01 {
    public static void main(String[] args) {
        var curso = new Curso(100, "Java", 5);
        var dao = DaoFactory.getInstance().getCursoDao(Util.tipo);
        dao.create(curso);
    }
}
