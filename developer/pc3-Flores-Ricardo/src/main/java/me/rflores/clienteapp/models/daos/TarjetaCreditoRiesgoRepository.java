package me.rflores.clienteapp.models.daos;

import me.rflores.clienteapp.models.entities.TarjetaCreditoRiesgo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaCreditoRiesgoRepository extends JpaRepository<TarjetaCreditoRiesgo, Long> {
}