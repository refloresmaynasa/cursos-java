package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.entidades.Alumno;
import me.rflores.modelo.entidades.Curso;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoMemory implements AlumnoDao {
    private List<Alumno> alumnos;

    public AlumnoDaoMemory() {
        this.alumnos = new ArrayList<>();
    }

    @Override
    public void create(Alumno entidad) {
        System.out.println("AlumnoDaoMemory.create");

        if (entidad != null && find(entidad.id()) == null)
            alumnos.add(entidad);
    }

    @Override
    public void update(Alumno entidad) {
        System.out.println("AlumnoDaoMemory.update");

        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).id() == entidad.id()) {
                alumnos.set(i, entidad);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        System.out.println("AlumnoDaoMemory.delete");

        for (Alumno alumno : alumnos) {
            if (alumno.id() == id)
                alumnos.remove(alumno);
        }
    }

    @Override
    public List<Alumno> findAll() {
        System.out.println("AlumnoDaoMemory.findAll");
        return alumnos;
    }

    @Override
    public Alumno find(Integer id) {
        System.out.println("AlumnoDaoMemory.find");
        for (Alumno alumno : alumnos) {
            if (alumno.id() == id)
                return alumno;
        }
        return null;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        System.out.println("AlumnoDaoMemory.findByNombre");
        return List.of();
    }

    @Override
    public List<Alumno> findByEstado(String estado) {
        System.out.println("AlumnoDaoMemory.findByEstado");
        return List.of();
    }
}
