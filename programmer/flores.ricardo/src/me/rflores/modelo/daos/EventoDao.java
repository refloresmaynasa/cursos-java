package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;

public interface EventoDao extends EntidadDao<Evento, Integer> {
    public boolean agregarAsistente(Evento evento, Asistente asistente);
}
