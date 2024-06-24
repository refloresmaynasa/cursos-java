package me.rflores.servicios;

import me.rflores.modelo.entidades.Curso;

import java.util.List;

public interface CursoService {
    public void grabar(Curso curso);
    public void actualizar(Curso curso);
    public void borrar(int id);
    public List<Curso> listar();
    public Curso buscar(int id);
}
