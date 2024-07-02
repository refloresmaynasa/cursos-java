package service.impl;

import daos.DaoFactory;
import daos.EventoDao;
import entidades.Evento;
import service.EventoService;
import utiles.Util;

import java.util.List;

public class EventoServiceImpl implements EventoService {
    private EventoDao dao;
    //falta incializar el dao con el Factory

    public EventoServiceImpl() {
        this.dao = DaoFactory.getEventoDao(Util.OPC);
    }

    @Override
    public List<Evento> listar() {
        return dao.findAll();
    }

    @Override
    public void grabar(Evento evento) {
        dao.create(evento);
    }

    @Override
    public void actualizar(Evento evento) {
        dao.update(evento);
    }

    @Override
    public void elininar(int id) {
        dao.delete(id);
    }

    @Override
    public Evento buscar(int id) {
        return dao.findById(id);
    }
}
