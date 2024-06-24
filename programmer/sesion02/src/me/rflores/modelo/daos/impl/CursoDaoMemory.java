package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.entidades.Curso;

import java.util.List;

public class CursoDaoMemory implements CursoDao {
    @Override
    public void create(Curso curso) {
        System.out.println("CursoDaoMemory.create");
    }

    @Override
    public void update(Curso curso) {
        System.out.println("CursoDaoMemory.update");
    }

    @Override
    public void delete(int id) {
        System.out.println("CursoDaoMemory.delete");
    }

    @Override
    public List<Curso> findAll() {
        System.out.println("CursoDaoMemory.findAll");
        return List.of();
    }

    @Override
    public Curso find(int id) {
        System.out.println("CursoDaoMemory.find");
        return null;
    }
}
