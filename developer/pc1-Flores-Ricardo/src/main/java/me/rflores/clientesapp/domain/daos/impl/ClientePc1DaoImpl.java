package me.rflores.clientesapp.domain.daos.impl;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import me.rflores.clientesapp.domain.daos.ClientePc1Dao;
import me.rflores.clientesapp.domain.entities.Cliente;

import java.util.List;

public class ClientePc1DaoImpl implements ClientePc1Dao {
    private EntityManager entityManager;

    public ClientePc1DaoImpl() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ClientePU");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void create(Cliente entity) {

    }

    @Override
    public void update(Cliente entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            var hql = "UPDATE Cliente SET nombre = :nombre, apellido = :apellido, telefono = :telefono, email = :email, totalCompras = :totalCompras WHERE codigo = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("nombre", entity.getNombre());
            query.setParameter("apellido", entity.getApellido());
            query.setParameter("telefono", entity.getTelefono());
            query.setParameter("email", entity.getEmail());
            query.setParameter("totalCompras", entity.getTotalCompras());
            query.setParameter("id", entity.getCodigo());

            int rowsUpdated = query.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            String hql = "DELETE FROM Cliente WHERE codigo = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", entity.getCodigo());

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> findAll() {
        String hql = "FROM Cliente";
        TypedQuery<Cliente> query = entityManager.createQuery(hql, Cliente.class);

        return query.getResultList();
    }

    @Override
    public Cliente getById(Integer id) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByCodigo", Cliente.class);
        query.setParameter("codigo", id);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Cliente> findByNombre(String nombre) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByNombre", Cliente.class);
        query.setParameter("nombre", "%" + nombre + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> findByApellido(String apellido) {
        TypedQuery<Cliente> query = entityManager.createNamedQuery("Cliente.findByApellido", Cliente.class);
        query.setParameter("apellido", "%" + apellido + "%");
        return query.getResultList();
    }

    @Override
    public List<Cliente> findByTotalComprasGreaterThan(double totalCompras) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Cliente> criteriaQuery = cb.createQuery(Cliente.class);
        Root<Cliente> clienteRoot = criteriaQuery.from(Cliente.class);

        Predicate totalComprasPredicate = cb.gt(clienteRoot.get("totalCompras"), totalCompras);
        criteriaQuery.where(totalComprasPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
