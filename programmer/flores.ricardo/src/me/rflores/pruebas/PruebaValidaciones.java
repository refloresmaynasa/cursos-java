package me.rflores.pruebas;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;
import me.rflores.modelo.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Ubicacion;

import java.time.LocalTime;

public class PruebaValidaciones {
    public static void main(String[] args) {
        EventoServicio servicio = new EventoServicioImpl();

        var asistente1 = new Asistente.Builder()
                .codigo(1)
                .apellidos("Perez")
                .nombre("Juan")
                .correo("juan@perez.com")
                .direccion("c. Calle1 #111")
                .telefono("+51 99999999")
                .build();

        var expositor = new Expositor.Builder()
                .codigo(1)
                .apellidos("Castro")
                .correo("jose@castro.com")
                .nombre("Jose")
                .sueldo(200.50D)
                .build();

        var evento1 = new Evento.Builder()
                .codigo(1)
                .horaIngreso(LocalTime.of(8, 30))
                .horaSalida(LocalTime.of(13, 0))
                .titulo("EVENTO 001")
                .temporadaAlta(true)
                .ubicacion(Ubicacion.VIP)
                .expositor(expositor)
                .build();

        var evento2 = new Evento.Builder()
                .codigo(2)
                .horaIngreso(LocalTime.of(9, 00))
                .horaSalida(LocalTime.of(13, 00))
                .titulo("EVENTO 002")
                .temporadaAlta(false)
                .ubicacion(Ubicacion.GOLD)
                .expositor(expositor)
                .build();

        servicio.grabar(evento1);
        // No deberia agregar un evento dos veces
        servicio.grabar(evento1);

        servicio.grabar(evento2);

        // agregar asistente1 al evento1 -> OK
        if (!servicio.agregarAsistente(evento1, asistente1))
            System.out.println("Nose pudo agregar al asistente 1");

        // agregar asistente1 al evento2 -> NO DEBERIA AGREGARSE
        if (!servicio.agregarAsistente(evento1, asistente1))
            System.out.println("Nose pudo agregar al asistente 1");


        for (var evento : servicio.listar()){
            System.out.println("=====================================");
            System.out.println("-> " + evento);
            System.out.println("Costo de Ingreso de Evento: " + evento.obtenerCostoIngreso());
            System.out.println("Monto de Descuento de Evento: " + evento.calcularMontoDescuento());
            System.out.println("Monto Total a Pagar de Evento: " + evento.calcularMontoTotalAPagar());
            System.out.println("Total Recaudado de Evento: " + evento.calcularTotalRecaudado());
        }
    }
}
