package daos.impl;

import daos.EventoDao;
import entidades.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDaoImplMemory implements EventoDao {
    private List<Evento> eventos;

    public EventoDaoImplMemory() {
        this.eventos = new ArrayList<>();
    }

    @Override
    public List<Evento> findAll() {
        return this.eventos;
    }

    @Override
    public void create(Evento evento) {
        this.eventos.add(evento);
    }

    @Override
    public void update(Evento evento) {

    }

    @Override
    public void delete(int ind) {

    }

    @Override
    public Evento findById(int ind) {
        return null;
    }
}
