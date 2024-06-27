package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public interface AlumnoDao extends EntidadDao<Alumno, Integer> {
    public List<Alumno> findByNombre(String nombre);

    public List<Alumno> findByEstado(String estado);

    public List<Alumno> findAllOrderByNombre();

    public List<Alumno> findAllOrderByPromedio();

    public float getPromedioAlumnos();

    public List<Alumno> findAllByPromedio(double min, double max);
}
