package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.entidades.Curso;

import java.util.List;

public class CursoDaoXml implements CursoDao {
    @Override
    public void create(Curso curso) {
        System.out.println("CursoDaoXml.create");
    }

    @Override
    public void update(Curso curso) {
        System.out.println("CursoDaoXml.update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("CursoDaoXml.delete");
    }

    @Override
    public List<Curso> findAll() {
        System.out.println("CursoDaoXml.findAll");
        return List.of();
    }

    @Override
    public Curso find(Integer id) {
        System.out.println("CursoDaoXml.find");
        return null;
    }

    @Override
    public List<Curso> findByRangeCreditos(Integer minimo, Integer maximo) {
        System.out.println("CursoDaoXml.findByRangeCreditos");
        return List.of();
    }
}
