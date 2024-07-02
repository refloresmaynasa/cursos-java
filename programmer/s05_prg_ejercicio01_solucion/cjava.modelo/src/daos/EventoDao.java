package daos;

import entidades.Evento;

import java.util.List;

public interface EventoDao {
    public List<Evento> findAll();
    public void create(Evento evento);
    public void update(Evento evento);
    public void delete(int ind);
    public Evento findById(int ind);
}
