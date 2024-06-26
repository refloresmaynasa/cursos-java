package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Curso;

import java.util.List;

public interface CursoDao extends EntidadDao<Curso, Integer> {
    public List<Curso> findByRangeCreditos(Integer minimo, Integer maximo);
}
