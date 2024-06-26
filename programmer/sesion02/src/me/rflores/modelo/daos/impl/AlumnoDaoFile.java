package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public class AlumnoDaoFile implements AlumnoDao {
    @Override
    public void create(Alumno entidad) {
        System.out.println("AlumnoDaoFile.create");
    }

    @Override
    public void update(Alumno entidad) {
        System.out.println("AlumnoDaoFile.update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("AlumnoDaoFile.delete");
    }

    @Override
    public List<Alumno> findAll() {
        System.out.println("AlumnoDaoFile.findAll");
        return List.of();
    }

    @Override
    public Alumno find(Integer id) {
        System.out.println("AlumnoDaoFile.find");
        return null;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        System.out.println("AlumnoDaoFile.findByNombre");
        return List.of();
    }

    @Override
    public List<Alumno> findByEstado(String estado) {
        System.out.println("AlumnoDaoFile.findByEstado");
        return List.of();
    }
}
