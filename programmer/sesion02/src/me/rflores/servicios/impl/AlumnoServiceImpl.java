package me.rflores.servicios.impl;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.daos.impl.DaoFactory;
import me.rflores.modelo.entidades.Alumno;
import me.rflores.servicios.AlumnoService;
import me.rflores.util.Util;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoDao dao;

    public AlumnoServiceImpl() {
        dao = DaoFactory.getInstance().getAlumnoDao(Util.tipo);
    }

    @Override
    public void grabar(Alumno alumno) {
        dao.create(alumno);
    }

    @Override
    public List<Alumno> listar() {
        return dao.findAll();
    }
}
