package me.rflores.mvcclientes.models.daos;

import me.rflores.mvcclientes.models.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}