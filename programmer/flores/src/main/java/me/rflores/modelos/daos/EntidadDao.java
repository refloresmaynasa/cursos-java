package me.rflores.modelos.daos;

import java.util.List;

public interface EntidadDao<T, V> {
    public void create(T entidad);

    public void update(T entidad);

    public void delete(V id);

    public List<T> findAll();

    public T find(V id);
}
