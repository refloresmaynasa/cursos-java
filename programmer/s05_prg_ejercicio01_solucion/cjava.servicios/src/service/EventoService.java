package service;



import entidades.Evento;

import java.util.List;

public interface EventoService {
    public List<Evento> listar();
    public void grabar (Evento evento);
    public void actualizar (Evento evento);
    public void elininar (int id);
    public Evento buscar(int id);

}
