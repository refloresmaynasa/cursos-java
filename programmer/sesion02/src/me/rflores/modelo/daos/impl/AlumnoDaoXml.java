package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public class AlumnoDaoXml implements AlumnoDao {
    @Override
    public void create(Alumno entidad) {
        System.out.println("AlumnoDaoXml.create");
    }

    @Override
    public void update(Alumno entidad) {
        System.out.println("AlumnoDaoXml.update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("AlumnoDaoXml.delete");
    }

    @Override
    public List<Alumno> findAll() {
        System.out.println("AlumnoDaoXml.findAll");
        return List.of();
    }

    @Override
    public Alumno find(Integer id) {
        System.out.println("AlumnoDaoXml.find");
        return null;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        System.out.println("AlumnoDaoXml.findByNombre");
        return List.of();
    }

    @Override
    public List<Alumno> findByEstado(String estado) {
        System.out.println("AlumnoDaoXml.findByEstado");
        return List.of();
    }
}
