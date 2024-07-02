package me.rflores.modelo.daos.impl;

import me.rflores.modelo.daos.EventoDao;
import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;

import java.util.LinkedList;
import java.util.List;

public class EventoDaoMemoria implements EventoDao {
    private final List<Evento> eventos;

    public EventoDaoMemoria() {
        this.eventos = new LinkedList<>();
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        if (!existeEvento(evento)) {
            System.out.println("Evento no registrado!!!");
            return false;
        }

        if (this.eventos.stream().anyMatch(e -> e.asistenteYaAgregado(asistente)))
        {
            System.out.println("El asistente que desea registrar ya se encuentra registrado en otro Evento");
            return false;
        }

        var eventoEncontrado = find(evento.getCodigo());
        eventoEncontrado.agregarAsistente(asistente);

        return true;
    }

    @Override
    public void create(Evento evento) {
        if (!existeEvento(evento)) {
            eventos.add(evento);
        } else {
            System.out.println("Ya existe un Evento con los mismos datos");
        }
    }

    @Override
    public void update(Evento evento) {
        var eventoEncontrado = find(evento.getCodigo());
        if (eventoEncontrado != null) {
            var index = eventos.indexOf(eventoEncontrado);
            eventos.set(index, evento);
        }
    }

    @Override
    public void delete(Integer id) {
        var evento = find(id);
        if (evento != null) {
            eventos.remove(evento);
        }
    }

    @Override
    public List<Evento> findAll() {
        return eventos;
    }

    @Override
    public Evento find(Integer id) {
        return this.eventos.stream().filter(e -> e.getCodigo() == id)
                .findFirst().orElse(null);
    }

    private boolean existeEvento(Evento evento) {
        return this.eventos.stream().anyMatch(e -> e.equals(evento));
    }
}
