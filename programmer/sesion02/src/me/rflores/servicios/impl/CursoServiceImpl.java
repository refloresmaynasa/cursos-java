package me.rflores.servicios.impl;

import me.rflores.modelo.daos.CursoDao;
import me.rflores.modelo.daos.impl.DaoFactory;
import me.rflores.modelo.entidades.Curso;
import me.rflores.servicios.CursoService;
import me.rflores.util.Util;

import java.util.List;

public class CursoServiceImpl implements CursoService {
    private final CursoDao dao;

    public CursoServiceImpl() {
        dao = DaoFactory.getInstance().getCursoDao(Util.tipo);
    }

    @Override
    public void grabar(Curso curso) {
        dao.create(curso);
    }

    @Override
    public void actualizar(Curso curso) {
        dao.update(curso);
    }

    @Override
    public void borrar(int id) {
        dao.delete(id);
    }

    @Override
    public List<Curso> listar() {
        return dao.findAll();
    }

    @Override
    public Curso buscar(int id) {
        return dao.find(id);
    }
}
