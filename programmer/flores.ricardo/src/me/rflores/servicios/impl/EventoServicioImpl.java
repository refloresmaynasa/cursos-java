package me.rflores.servicios.impl;

import me.rflores.modelo.daos.EventoDao;
import me.rflores.modelo.daos.impl.DaoFactory;
import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;
import me.rflores.servicios.EventoServicio;
import me.rflores.utiles.MisConstantes;

import java.util.Comparator;
import java.util.List;

public class EventoServicioImpl implements EventoServicio {
    private EventoDao dao;

    public EventoServicioImpl() {
        this.dao = DaoFactory.getInstance().obtenerEventoDao(MisConstantes.OPCION_DAO);
    }

    @Override
    public List<Evento> listar() {
        return this.dao.findAll();
    }

    @Override
    public List<Evento> listarPorTitulo(boolean desendente) {
        return desendente ? listar().stream().sorted(Comparator.comparing(Evento::getTitulo).reversed())
                .toList()
                : listar().stream().sorted(Comparator.comparing(Evento::getTitulo))
                .toList();
    }

    @Override
    public void grabar(Evento evento) {
        this.dao.create(evento);
    }

    @Override
    public void actualizar(Evento evento) {
        this.dao.update(evento);
    }

    @Override
    public void eliminar(int id) {
        this.dao.delete(id);
    }

    @Override
    public Evento buscar(int id) {
        return this.dao.find(id);
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        return this.dao.agregarAsistente(evento, asistente);
    }
}
