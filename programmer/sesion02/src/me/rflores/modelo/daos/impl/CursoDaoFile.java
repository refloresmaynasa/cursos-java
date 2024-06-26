package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.entidades.Curso;

import java.util.List;

public class CursoDaoFile implements CursoDao {
    @Override
    public void create(Curso curso) {
        System.out.println("CursoDaoFile.create");
    }

    @Override
    public void update(Curso curso) {
        System.out.println("CursoDaoFile.update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("CursoDaoFile.delete");
    }

    @Override
    public List<Curso> findAll() {
        System.out.println("CursoDaoFile.findAll");
        return List.of();
    }

    @Override
    public Curso find(Integer id) {
        System.out.println("CursoDaoFile.find");
        return null;
    }

    @Override
    public List<Curso> findByRangeCreditos(Integer minimo, Integer maximo) {
        System.out.println("CursoDaoFile.findByRangeCreditos");
        return List.of();
    }
}
