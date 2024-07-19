package me.rflores.servicios;

import junit.framework.TestCase;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Periodo;
import me.rflores.utiles.Util;

import java.time.LocalDate;

public class EventoServicioTest extends TestCase {
    private EventoServicio servicio = new EventoServicioImpl();

    public void testListarPorFecha() {
        Util.cargarDatos();

        System.out.println("=======================================");
        System.out.println("Lista de eventos por fecha descendente:");
        servicio.listarPorFecha(true)
            .forEach(System.out::println);

        System.out.println("=======================================");
        System.out.println("Lista de eventos por fecha ascendente:");
        servicio.listarPorFecha(false)
            .forEach(System.out::println);
    }

    public void testListarPorFechas() {
        Util.cargarDatos();
        var fechaInicial = LocalDate.of(2024, 3,1);
        var fechaFinal = LocalDate.of(2024, 9,30);
        System.out.println("=======================================");
        System.out.println("Lista de eventos por rango de fechas:");
        servicio.listarPorFechas(fechaInicial, fechaFinal)
            .forEach(System.out::println);
    }

    public void testBuscarPorCapacidad() throws Exception {
        Util.cargarDatos();
        System.out.println("=======================================");
        System.out.println("Evento con mayor capacidad");
        var eventoMaximo = servicio.buscarPorCapacidad(false);
        eventoMaximo.imprimirInfoCorta();

        System.out.println("=======================================");
        System.out.println("Evento con menor capacidad");
        var eventoMinimo = servicio.buscarPorCapacidad(true);
        eventoMinimo.imprimirInfoCorta();
    }

    public void testInformacionCostos() {
        Util.cargarDatos();
        servicio.informacionCostos();
    }

    public void testInformacionFechas() {
        Util.cargarDatos();
        servicio.informacionFechas();
    }

    public void testInformacionPorPeriodo() {
        Util.cargarDatos();
        servicio.informacionPorPeriodo(Periodo.Q3, 2024);
    }
}