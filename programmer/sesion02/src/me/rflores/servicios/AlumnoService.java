package me.rflores.servicios;

import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public interface AlumnoService {
    public void grabar(Alumno alumno);

    public List<Alumno> listar();
}
