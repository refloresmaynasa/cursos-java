package me.rflores.modelo.entidades;

import me.rflores.util.MisConstantes;
import me.rflores.util.Ubicacion;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Evento {
    private final String titulo;
    private final Duration duracion;
    private final LocalTime horaIngreso;
    private final LocalTime horaSalida;
    private final boolean temporadaAlta;
    private final Ubicacion ubicacion;

    private final Expositor expositor;
    private final List<Asistente> asistentes;

    private Evento(Builder builder) {
        this.titulo = builder.titulo;
        this.horaIngreso = builder.horaIngreso;
        this.horaSalida = builder.horaSalida;
        this.temporadaAlta = builder.temporadaAlta;
        this.ubicacion = builder.ubicacion;
        this.expositor = builder.expositor;

        this.duracion = Duration.between(this.horaIngreso, this.horaSalida);
        this.asistentes = new LinkedList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public boolean isTemporadaAlta() {
        return temporadaAlta;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Expositor getExpositor() {
        return expositor;
    }

    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    public double obtenerCostoIngreso() {
        return this.ubicacion.getCosto();
    }

    public double calcularMontoTotalAPagar() {
        var impuesto = obtenerCostoIngreso() * MisConstantes.IGV;
        return obtenerCostoIngreso() + impuesto;
    }

    public double calcularMontoDescuento() {
        return isTemporadaAlta() ? obtenerCostoIngreso() * MisConstantes.DESCUENTO_TEMPORADA_ALTA :
                obtenerCostoIngreso() * MisConstantes.DESCUENTO_TEMPORADA_BAJA;
    }

    public boolean AsistenteYaAgregado(Asistente asistente) {
        if (asistente == null)
            return false;

        return this.asistentes.stream()
                .anyMatch(a -> asistente == a);
    }

    public boolean AgregarAsistente(Asistente asistente) {
        if (asistente == null || AsistenteYaAgregado(asistente))
            return false;

        return asistentes.add(asistente);
    }

    public double CalcularTotalRecaudado() {
        return asistentes.size() * obtenerCostoIngreso();
    }

    public static class Builder {
        private String titulo;
        private LocalTime horaIngreso;
        private LocalTime horaSalida;
        private boolean temporadaAlta;
        private Ubicacion ubicacion;
        private Expositor expositor;

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder horaIngreso(LocalTime horaIngreso) {
            this.horaIngreso = horaIngreso;
            return this;
        }

        public Builder horaSalida(LocalTime horaSalida) {
            this.horaSalida = horaSalida;
            return this;
        }

        public Builder temporadaAlta(boolean temporadaAlta) {
            this.temporadaAlta = temporadaAlta;
            return this;
        }

        public Builder ubicacion(Ubicacion ubicacion) {
            this.ubicacion = ubicacion;
            return this;
        }

        public Builder expositor(Expositor expositor) {
            this.expositor = expositor;
            return this;
        }

        public Evento build() {
            return new Evento(this);
        }
    }
}
