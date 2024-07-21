package me.rflores.modelos.entidades;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.rflores.utiles.Constantes;
import me.rflores.utiles.Periodo;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(builder = Evento.Builder.class)
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String titulo;
    private final Duration duracion;
    private final LocalTime horaIngreso;
    private final LocalTime horaSalida;
    private final boolean temporadaAlta;
    private final Categoria categoria;

    private final Expositor expositor;
    private final List<Asistente> asistentes;

    private final LocalDate fecha;
    private final String direccion;
    private final int capacidad;
    private final double costo;

    private static final DateTimeFormatter formatoFechaInfoCorta = DateTimeFormatter.ofPattern("MMMM/dd/EEEE");

    private Evento(Builder builder) {
        this.id = builder.id;
        this.titulo = builder.titulo;
        this.horaIngreso = builder.horaIngreso;
        this.horaSalida = builder.horaSalida;
        this.temporadaAlta = builder.temporadaAlta;
        this.categoria = builder.categoria == null ? Categoria.SILVER : builder.categoria;
        this.expositor = builder.expositor;

        if (this.horaIngreso != null && this.horaSalida != null) {
            this.duracion = Duration.between(this.horaIngreso, this.horaSalida);
        } else {
            this.duracion = Duration.ofMillis(0);
        }
        this.asistentes = new LinkedList<>();

        this.fecha = builder.fecha == null ? LocalDate.now() : builder.fecha;
        this.direccion = builder.direccion;
        this.capacidad = builder.capacidad;
        this.costo = this.categoria.getCosto();
    }

    public int getId() {
        return id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Expositor getExpositor() {
        return expositor;
    }

    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Evento evento)) {
            return false;
        }

        return evento.id == this.id
            && Objects.equals(evento.titulo, this.titulo);
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + id +
            ", titulo='" + titulo + '\'' +
            ", duracion=" + duracion.toHoursPart() + ":" + duracion.toMinutesPart() + ":" + duracion.toSecondsPart() +
            ", horaIngreso=" + horaIngreso +
            ", horaSalida=" + horaSalida +
            ", temporadaAlta=" + temporadaAlta +
            ", categoria=" + categoria +
            ", expositor=" + expositor +
            ", asistentes=" + asistentes +
            ", fecha=" + fecha +
            ", direccion='" + direccion + '\'' +
            ", capacidad=" + capacidad +
            ", costo=" + costo +
            '}';
    }

    public double obtenerCostoIngreso() {
        return this.categoria.getCosto();
    }

    public String getPeriodo() {
        return fecha.getYear() + "-" + Periodo.valueOf(fecha.getMonth());
    }

    public double calcularMontoTotalAPagar() {
        var impuesto = obtenerCostoIngreso() * Constantes.IGV;
        return obtenerCostoIngreso() + impuesto;
    }

    public double calcularMontoDescuento() {
        return isTemporadaAlta() ? obtenerCostoIngreso() * Constantes.DESCUENTO_TEMPORADA_ALTA :
            obtenerCostoIngreso() * Constantes.DESCUENTO_TEMPORADA_BAJA;
    }

    public boolean seTraslapa(Evento otroEvento) {
        if (!fecha.equals(otroEvento.getFecha())) {
            return false;
        }
        return horaIngreso.isBefore(otroEvento.getHoraSalida()) && horaSalida.isAfter(otroEvento.getHoraIngreso());
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

    public void imprimirInfoCorta() {
        System.out.println("Evento: " + titulo);
        System.out.println("Fecha (mes/dia/dia de semana): " + formatoFechaInfoCorta.format(fecha).toUpperCase());
        System.out.println("Capacidad: " + capacidad);
    }

    public String resumen() {
        return "Evento {" +
            "id=" + id +
            ", PERIODO=" + getPeriodo() +
            ", titulo='" + titulo + '\'' +
            ", categoria=" + categoria +
            ", expositor=" + expositor.nombre() +
            ", asistentes=" + asistentes.size() +
            ", fecha=" + fecha +
            ", capacidad=" + capacidad +
            '}';
    }

    public static class Builder {
        private int id;
        private String titulo;
        private LocalTime horaIngreso;
        private LocalTime horaSalida;
        private boolean temporadaAlta;
        private Categoria categoria;
        private Expositor expositor;
        private LocalDate fecha;
        private String direccion;
        private int capacidad;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder withHoraIngreso(LocalTime horaIngreso) {
            this.horaIngreso = horaIngreso;
            return this;
        }

        public Builder withHoraSalida(LocalTime horaSalida) {
            this.horaSalida = horaSalida;
            return this;
        }

        public Builder withTemporadaAlta(boolean temporadaAlta) {
            this.temporadaAlta = temporadaAlta;
            return this;
        }

        public Builder withCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder withExpositor(Expositor expositor) {
            this.expositor = expositor;
            return this;
        }

        public Builder withFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder withDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder withCapacidad(int capacidad) {
            this.capacidad = capacidad;
            return this;
        }

        public Evento build() {
            return new Evento(this);
        }
    }
}
