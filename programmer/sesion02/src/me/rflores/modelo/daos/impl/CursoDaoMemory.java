package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.entidades.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoDaoMemory implements CursoDao {

    private List<Curso> cursos;

    public CursoDaoMemory() {
        this.cursos = new ArrayList<>();
    }

    @Override
    public void create(Curso curso) {
        System.out.println("CursoDaoMemory.create");

        if (curso != null && find(curso.id()) == null)
            cursos.add(curso);
    }

    @Override
    public void update(Curso curso) {
        System.out.println("CursoDaoMemory.update");

        for(int i = 0; i < cursos.size(); i++){
            if(cursos.get(i).id() == curso.id()){
                cursos.set(i,curso);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        System.out.println("CursoDaoMemory.delete");
        for (Curso curso : cursos) {
            if (curso.id() == id)
                cursos.remove(curso);
        }
    }

    @Override
    public List<Curso> findAll() {
        System.out.println("CursoDaoMemory.findAll");
        return cursos;
    }

    @Override
    public Curso find(Integer id) {
        System.out.println("CursoDaoMemory.find");

        for (Curso curso : cursos) {
            if (curso.id() == id)
                return curso;
        }
        return null;
    }

    @Override
    public List<Curso> findByRangeCreditos(Integer minimo, Integer maximo) {
        System.out.println("CursoDaoMemory.findByRangeCreditos");

        List<Curso> cursosFounded = new ArrayList<>();
        for(Curso curso : cursos){
            if(curso.creditos() >= minimo && curso.creditos() <= maximo){
                cursosFounded.add(curso);
            }
        }
        return cursosFounded;
    }
}
