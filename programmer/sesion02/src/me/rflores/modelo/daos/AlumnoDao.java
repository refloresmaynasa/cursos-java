package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public interface AlumnoDao extends EntidadDao<Alumno, Integer> {
    public List<Alumno> findByNombre(String nombre);

    public List<Alumno> findByEstado(String estado);
}
