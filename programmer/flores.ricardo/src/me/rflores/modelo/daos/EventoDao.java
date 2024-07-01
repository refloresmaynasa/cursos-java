package me.rflores.modelo.daos;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;

public interface EventoDao extends EntidadDao<Evento, String> {
    public boolean agregarAsistente(Asistente asistente);
}
