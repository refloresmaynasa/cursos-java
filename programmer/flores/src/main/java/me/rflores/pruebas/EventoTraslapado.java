package me.rflores.pruebas;

import me.rflores.modelos.entidades.Categoria;
import me.rflores.modelos.entidades.Evento;
import me.rflores.modelos.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoTraslapado {
    private static EventoServicio servicio = new EventoServicioImpl();

    public static void main(String[] args) {
        ejecutarPruebaTraslapado();
    }

    public static void ejecutarPruebaTraslapado() {
        System.out.println("\n====== Prueba de Evento Traslapado ======");

        var eventoId = 11;
        var expositor = new Expositor(1, "Pablo", "Espinoza", 2800D, "pablo@expositor.com");
        var evento = new Evento.Builder().id(eventoId)
            .titulo("Evento 1").fecha(LocalDate.of(2024, 1, 2))
            .horaIngreso(LocalTime.of(8, 0)).horaSalida(LocalTime.of(9, 0))
            .categoria(Categoria.SILVER).capacidad(30).temporadaAlta(true)
            .expositor(expositor).direccion("direccion del evento 1").build();

        var eventoTraslapado = new Evento.Builder().id(12)
            .titulo("Evento 2").fecha(LocalDate.of(2024, 1, 2))
            .horaIngreso(LocalTime.of(8, 30)).horaSalida(LocalTime.of(9, 30))
            .categoria(Categoria.SILVER).capacidad(30).temporadaAlta(true)
            .expositor(expositor).direccion("direccion del evento 2").build();

        servicio.grabar(evento);
        System.out.println("* Evento 1 grabado: \n" + evento);
        System.out.println("* Evento 2 a grabar: \n" + eventoTraslapado);
        servicio.grabar(eventoTraslapado);

        eventoTraslapado = new Evento.Builder().id(12)
            .titulo("Evento 2").fecha(LocalDate.of(2024, 1, 2))
            .horaIngreso(LocalTime.of(9, 0)).horaSalida(LocalTime.of(10, 0))
            .categoria(Categoria.SILVER).capacidad(30).temporadaAlta(true)
            .expositor(expositor).direccion("direccion del evento 2").build();

        System.out.println("* Evento 2 ajustado a grabar: \n" + eventoTraslapado);
        servicio.grabar(eventoTraslapado);
        System.out.println("-- Listar Eventos guardados --");
        servicio.listar().forEach(System.out::println);

        servicio.eliminar(eventoId);
        servicio.eliminar(12);
    }
}
