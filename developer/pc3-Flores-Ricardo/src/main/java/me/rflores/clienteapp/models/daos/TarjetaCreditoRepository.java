package me.rflores.clienteapp.models.daos;

import me.rflores.clienteapp.models.entities.TarjetaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito, Long> {
}