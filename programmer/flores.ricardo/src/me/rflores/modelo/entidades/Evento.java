package me.rflores.modelo.entidades;

import me.rflores.utiles.MisConstantes;
import me.rflores.utiles.Ubicacion;

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Evento {
    private final int codigo;
    private final String titulo;
    private final Duration duracion;
    private final LocalTime horaIngreso;
    private final LocalTime horaSalida;
    private final boolean temporadaAlta;
    private final Ubicacion ubicacion;

    private final Expositor expositor;
    private final List<Asistente> asistentes;

    private Evento(Builder builder) {
        this.codigo = builder.codigo;
        this.titulo = builder.titulo;
        this.horaIngreso = builder.horaIngreso;
        this.horaSalida = builder.horaSalida;
        this.temporadaAlta = builder.temporadaAlta;
        this.ubicacion = builder.ubicacion;
        this.expositor = builder.expositor;

        this.duracion = Duration.between(this.horaIngreso, this.horaSalida);
        this.asistentes = new LinkedList<>();
    }

    public int getCodigo() {
        return codigo;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Evento evento)) {
            return false;
        }

        return evento.codigo == this.codigo
                && Objects.equals(evento.titulo, this.titulo);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion.toHoursPart() + ":" + duracion.toMinutesPart() + ":" + duracion.toSecondsPart() +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", temporadaAlta=" + temporadaAlta +
                ", ubicacion=" + ubicacion +
                ", expositor=" + expositor +
                ", asistentes=" + asistentes +
                '}';
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

    public boolean asistenteYaAgregado(Asistente asistente) {
        if (asistente == null)
            return false;

        return this.asistentes.stream()
                .anyMatch(a -> asistente == a);
    }

    public boolean agregarAsistente(Asistente asistente) {
        if (asistente == null || asistenteYaAgregado(asistente))
            return false;

        return asistentes.add(asistente);
    }

    public double calcularTotalRecaudado() {
        return asistentes.size() * obtenerCostoIngreso();
    }

    public static class Builder {
        private int codigo;
        private String titulo;
        private LocalTime horaIngreso;
        private LocalTime horaSalida;
        private boolean temporadaAlta;
        private Ubicacion ubicacion;
        private Expositor expositor;

        public Builder codigo(int codigo) {
            this.codigo = codigo;
            return this;
        }

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
