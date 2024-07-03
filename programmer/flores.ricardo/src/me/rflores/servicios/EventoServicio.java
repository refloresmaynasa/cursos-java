package me.rflores.servicios;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;

import java.util.List;

public interface EventoServicio {
    public List<Evento> listar();

    public void grabar(Evento evento);

    public void actualizar(Evento evento);

    public void eliminar(int id);

    public Evento buscar(int id);

    public boolean agregarAsistente(Evento evento, Asistente asistente);
}
