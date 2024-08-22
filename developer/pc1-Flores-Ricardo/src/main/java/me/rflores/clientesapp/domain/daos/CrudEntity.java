package me.rflores.clientesapp.domain.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface CrudEntity<T, V> {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClientePU");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    default void create(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    default void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    default void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    default List<T> findAll() {
        var object = getEntityClass();
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((Class<T>) object);
        Root<T> root = criteriaQuery.from((Class<T>) object);
        criteriaQuery.select(root);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    default T getById(V id) {
        entityManager.getTransaction().begin();
        entityManager.find(getEntityClass(), id);
        entityManager.getTransaction().commit();
        return null;
    }

    Class<T> getEntityClass();
}
