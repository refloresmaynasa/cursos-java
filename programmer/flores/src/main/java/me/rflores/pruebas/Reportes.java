package me.rflores.pruebas;

import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Periodo;
import me.rflores.utiles.Util;

import java.time.LocalDate;

public class Reportes {
    private static EventoServicio servicio = new EventoServicioImpl();

    public static void main(String[] args) throws Exception {
        Util.cargarDatos();
        generarReportes();
    }

    public static void generarReportes() throws Exception {
        System.out.println("\n====== Prueba de Reportes ======");

        System.out.println("------------------------------------");
        System.out.println("EVENTOS POR FECHA DESCENDENTE\n");
        servicio.listarPorFecha(true)
            .forEach(System.out::println);

        System.out.println("------------------------------------");
        System.out.println("EVENTOS POR FECHA ASCENDENTE\n");
        servicio.listarPorFecha(false)
            .forEach(System.out::println);

        var fechaInicial = LocalDate.of(2024, 3, 1);
        var fechaFinal = LocalDate.of(2024, 9, 30);
        System.out.println("=======================================");
        System.out.println("EVENTOS POR RANGO DE FECHAS (" + fechaInicial + " - " + fechaFinal + ")\n");
        servicio.listarPorFechas(fechaInicial, fechaFinal)
            .forEach(System.out::println);

        System.out.println("=======================================");
        System.out.println("EVENTO CON MAYOR CAPACIDAD");
        var eventoMaximo = servicio.buscarPorCapacidad(false);
        eventoMaximo.imprimirInfoCorta();

        System.out.println("=======================================");
        System.out.println("EVENTO CON MENOR CAPACIDAD");
        var eventoMinimo = servicio.buscarPorCapacidad(true);
        eventoMinimo.imprimirInfoCorta();

        System.out.println("=======================================");
        System.out.println("INFORMACION DE COSTOS");
        servicio.informacionCostos();

        System.out.println("=======================================");
        System.out.println("INFORMACION POR FECHAS");
        servicio.informacionFechas();

        System.out.println("=======================================");
        System.out.println("+ REPORTE POR PERIODO");
        servicio.informacionPorPeriodo(Periodo.Q3, 2024);
    }
}
