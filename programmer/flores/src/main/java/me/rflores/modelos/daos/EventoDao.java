package me.rflores.modelos.daos;


import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Evento;

public interface EventoDao extends EntidadDao<Evento, Integer> {
    public boolean agregarAsistente(Evento evento, Asistente asistente);
}
