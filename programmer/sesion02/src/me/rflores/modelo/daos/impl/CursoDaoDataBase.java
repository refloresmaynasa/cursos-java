package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.entidades.Curso;

import java.util.List;

public class CursoDaoDataBase implements CursoDao {
    @Override
    public void create(Curso curso) {
        System.out.println("CursoDaoDataBase.create");
    }

    @Override
    public void update(Curso curso) {
        System.out.println("CursoDaoDataBase.update");
    }

    @Override
    public void delete(int id) {
        System.out.println("CursoDaoDataBase.delete");
    }

    @Override
    public List<Curso> findAll() {
        System.out.println("CursoDaoDataBase.findAll");
        return List.of();
    }

    @Override
    public Curso find(int id) {
        System.out.println("CursoDaoDataBase.find");
        return null;
    }
}
