package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Curso;

import java.util.List;

public interface CursoDao {
    public void create(Curso curso);

    public void update(Curso curso);

    public void delete(int id);

    public List<Curso> findAll();

    public Curso find(int id);
}
