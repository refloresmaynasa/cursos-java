package me.rflores.clienteapp.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarjeta_credito")
public class TarjetaCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private LocalDate ultimoPago;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(LocalDate ultimoPago) {
        this.ultimoPago = ultimoPago;
    }
}
