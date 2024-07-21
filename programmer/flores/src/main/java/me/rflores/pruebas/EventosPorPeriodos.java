package me.rflores.pruebas;

import me.rflores.modelos.entidades.Categoria;
import me.rflores.modelos.entidades.Evento;
import me.rflores.modelos.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EventosPorPeriodos {
    private static EventoServicio servicio = new EventoServicioImpl();

    public static void main(String[] args) {
        Util.cargarDatos();
        ejecutarEventosPorPeriodos();
    }

    public static void ejecutarEventosPorPeriodos() {
        System.out.println("\n====== Prueba Eventos por Periodo ======");

        var expositor = new Expositor(110, "Luis", "Domenech", 3000D, "example@example.com");

        var evento1 = new Evento.Builder().withId(11).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("HTML 2023").withTemporadaAlta(false).withCategoria(Categoria.SILVER).withExpositor(expositor).withCapacidad(20)
            .withFecha(LocalDate.of(2023, 8, 7)).withDireccion("c. Calle 1 #123")
            .build();
        var evento2 = new Evento.Builder().withId(12).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("HTML 2025").withTemporadaAlta(false).withCategoria(Categoria.SILVER).withExpositor(expositor).withCapacidad(20)
            .withFecha(LocalDate.of(2025, 3, 7)).withDireccion("c. Calle 1 #123")
            .build();

        servicio.grabar(evento1);
        servicio.grabar(evento2);

        var periodos = servicio.listar().parallelStream().sorted(Comparator.comparing(Evento::getFecha)).collect(
            Collectors.groupingBy(Evento::getPeriodo));
        Map<String, List<Evento>> ordenados = new TreeMap<>(periodos);

        ordenados.forEach((periodo, eventos) -> {
            System.out.println("---------------------------");
            System.out.println("PERIODO: " + periodo);
            eventos.forEach(System.out::println);
        });
    }
}
