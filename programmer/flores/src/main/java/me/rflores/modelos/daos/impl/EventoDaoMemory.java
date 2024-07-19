package me.rflores.modelos.daos.impl;

import me.rflores.modelos.daos.EventoDao;
import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Evento;

import java.util.LinkedList;
import java.util.List;

public class EventoDaoMemory implements EventoDao {
    private static List<Evento> eventos;

    public EventoDaoMemory() {
        this.eventos = new LinkedList<>();
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        if (!existeEvento(evento)) {
            System.out.println("Evento no registrado!!!");
            return false;
        }

        if (this.eventos.stream().anyMatch(e -> e.asistenteYaAgregado(asistente))) {
            System.out.println("El asistente que desea registrar ya se encuentra registrado en otro Evento");
            return false;
        }

        var eventoEncontrado = find(evento.getId());
        eventoEncontrado.agregarAsistente(asistente);

        return true;
    }

    @Override
    public void create(Evento evento) {
        if (!existeEvento(evento)) {
            if (eventos.parallelStream().anyMatch(e -> e.seTraslapa(evento))) {
                System.out.println("ERROR: Se encontro un evento traslapado, revise la fecha y horas del nuevo Evento");
                return;
            }

            eventos.add(evento);
        } else {
            System.out.println("ERROR: Ya existe un Evento con los mismos datos");
        }
    }

    @Override
    public void update(Evento evento) {
        var eventoEncontrado = find(evento.getId());
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
        return this.eventos.stream().filter(e -> e.getId() == id)
            .findFirst().orElse(null);
    }

    private boolean existeEvento(Evento evento) {
        return this.eventos.stream().anyMatch(e -> e.equals(evento));
    }
}
