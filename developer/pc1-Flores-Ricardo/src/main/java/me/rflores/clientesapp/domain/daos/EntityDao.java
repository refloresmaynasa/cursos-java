package me.rflores.clientesapp.domain.daos;

import java.util.List;

public interface EntityDao<T, V> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T getById(V id);
}
