package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.entidades.Alumno;

import java.util.List;

public class AlumnoDaoDataBase implements AlumnoDao {
    @Override
    public void create(Alumno entidad) {
        System.out.println("AlumnoDaoDataBase.create");
    }

    @Override
    public void update(Alumno entidad) {
        System.out.println("AlumnoDaoDataBase.update");
    }

    @Override
    public void delete(Integer id) {
        System.out.println("AlumnoDaoDataBase.delete");
    }

    @Override
    public List<Alumno> findAll() {
        System.out.println("AlumnoDaoDataBase.findAll");
        return List.of();
    }

    @Override
    public Alumno find(Integer id) {
        System.out.println("AlumnoDaoDataBase.find");
        return null;
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        System.out.println("AlumnoDaoDataBase.findByNombre");
        return List.of();
    }

    @Override
    public List<Alumno> findByEstado(String estado) {
        System.out.println("AlumnoDaoDataBase.findByEstado");
        return List.of();
    }
}
