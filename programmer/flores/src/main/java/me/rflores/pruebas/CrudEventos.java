package me.rflores.pruebas;

import me.rflores.modelos.entidades.Categoria;
import me.rflores.modelos.entidades.Evento;
import me.rflores.modelos.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;

import java.time.LocalDate;
import java.time.LocalTime;

public class CrudEventos {
    private static EventoServicio servicio = new EventoServicioImpl();

    public static void main(String[] args) {
        ejecutarCrud();
    }

    public static void ejecutarCrud() {
        System.out.println("\n====== Pruebas CRUD ======");
        var eventoId = 11;
        var expositor = new Expositor(1, "Pablo", "Espinoza", 2800D, "pablo@expositor.com");
        var evento = new Evento.Builder().withId(eventoId)
            .withTitulo("Evento prueba1").withFecha(LocalDate.of(2024, 1, 2))
            .withHoraIngreso(LocalTime.of(8, 0)).withHoraSalida(LocalTime.of(9, 0))
            .withCategoria(Categoria.SILVER).withCapacidad(30).withTemporadaAlta(true)
            .withExpositor(expositor).withDireccion("direccion del evento1").build();

        servicio.grabar(evento);
        System.out.println("* Evento grabado correctamente: " + evento.getId());
        System.out.println("* Buscar Evento (grabado): \n" + servicio.buscar(eventoId));
        evento = new Evento.Builder().withId(eventoId)
            .withTitulo("Evento prueba 1 Modificado").withFecha(LocalDate.of(2024, 1, 2))
            .withHoraIngreso(LocalTime.of(10, 0)).withHoraSalida(LocalTime.of(11, 0))
            .withCategoria(Categoria.GOLD).withCapacidad(30).withTemporadaAlta(true)
            .withExpositor(expositor).withDireccion("direccion del evento1").build();
        servicio.actualizar(evento);
        System.out.println("* Buscar Evento (actualizado): \n" + servicio.buscar(eventoId));
        servicio.eliminar(eventoId);
        System.out.println("* Buscar Evento (eliminado): \n" + servicio.buscar(eventoId));
    }
}
