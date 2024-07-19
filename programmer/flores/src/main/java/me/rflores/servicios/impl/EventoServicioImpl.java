package me.rflores.servicios.impl;

import me.rflores.modelos.daos.EventoDao;
import me.rflores.modelos.daos.impl.DaoFactory;
import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Evento;
import me.rflores.servicios.CopiaSeguridad;
import me.rflores.servicios.EventoServicio;
import me.rflores.utiles.Config;
import me.rflores.utiles.Constantes;
import me.rflores.utiles.Periodo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class EventoServicioImpl implements EventoServicio {
    private EventoDao dao;
    private ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(1);


    public EventoServicioImpl() {
        this.dao = DaoFactory.getInstance().obtenerEventoDao(Constantes.OPCION_DAO);
    }

    @Override
    public List<Evento> listar() {
        return this.dao.findAll();
    }

    @Override
    public List<Evento> listarPorTitulo(boolean desendente) {
        return desendente ? listar().stream().sorted(Comparator.comparing(Evento::getTitulo).reversed())
            .toList()
            : listar().stream().sorted(Comparator.comparing(Evento::getTitulo))
            .toList();
    }

    @Override
    public List<Evento> listarPorFecha(boolean desendente) {
        return desendente ? listar().stream().sorted(Comparator.comparing(Evento::getFecha).reversed())
            .toList()
            : listar().stream().sorted(Comparator.comparing(Evento::getFecha))
            .toList();
    }

    @Override
    public List<Evento> listarPorFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        return listar().parallelStream().filter(e -> e.getFecha().isAfter(fechaInicial) &&
                e.getFecha().isBefore(fechaFinal))
            .toList();
    }

    @Override
    public void grabar(Evento evento) {
        this.dao.create(evento);
    }

    @Override
    public void actualizar(Evento evento) {
        this.dao.update(evento);
    }

    @Override
    public void eliminar(int id) {
        this.dao.delete(id);
    }

    @Override
    public Evento buscar(int id) {
        return this.dao.find(id);
    }

    @Override
    public Evento buscarPorCapacidad(boolean conMenorCapacidad) throws Exception {
        return conMenorCapacidad ?
            listar().stream()
                .min(Comparator.comparingInt(Evento::getCapacidad))
                .orElseThrow(Exception::new) :
            listar().stream()
                .max(Comparator.comparingInt(Evento::getCapacidad))
                .orElseThrow(Exception::new);
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        return this.dao.agregarAsistente(evento, asistente);
    }

    @Override
    public void informacionCostos() {
        var costosInfo = listar().stream().mapToDouble(Evento::getCosto)
            .summaryStatistics();
        var eventosConCostoMayorAlPromedio = listar().parallelStream()
            .filter(e -> e.getCosto() > costosInfo.getAverage()).count();

        System.out.println("Cantidad de Eventos: " + costosInfo.getCount());
        System.out.println("===============================");
        System.out.println("Costo Promedio: " + costosInfo.getAverage());
        System.out.println("Costo Maximo: " + costosInfo.getMax());
        System.out.println("Costo Minimo: " + costosInfo.getMin());
        System.out.println("Cantidad de eventos con costo mayor a la media: " + eventosConCostoMayorAlPromedio);
    }

    @Override
    public void informacionFechas() {
        Map<DayOfWeek, Long> eventosPorDiaSemana = listar().stream()
            .collect(Collectors.groupingBy(e -> e.getFecha().getDayOfWeek(), Collectors.counting()));

        Map<Month, Long> eventosPorMes = listar().stream()
            .collect(Collectors.groupingBy(e -> e.getFecha().getMonth(), Collectors.counting()));

        System.out.println("Cantidad de eventos por dia de la semana:");
        eventosPorDiaSemana.forEach((dia, cantidad) -> System.out.println(dia + ": " + cantidad));

        System.out.println("\nCantidad de Eventos por Mes:");
        eventosPorMes.forEach((mes, cantidad) -> System.out.println(mes + ": " + cantidad));

        Optional<Map.Entry<DayOfWeek, Long>> maxEventosPorDiaSemana = eventosPorDiaSemana.entrySet().stream()
            .max(Map.Entry.comparingByValue());

        Optional<Map.Entry<Month, Long>> maxEventosPorMes = eventosPorMes.entrySet().stream()
            .max(Map.Entry.comparingByValue());

        maxEventosPorDiaSemana.ifPresent(resultado ->
            System.out.println("\nEl dia de la semana con mas eventos es: " + resultado.getKey() + " con " + resultado.getValue() + " eventos"));

        maxEventosPorMes.ifPresent(resultado ->
            System.out.println("El mes con mas eventos es: " + resultado.getKey() + " con " + resultado.getValue() + " eventos"));
    }

    @Override
    public void informacionPorPeriodo(Periodo periodo, int anio) {
        System.out.println("=============================================");
        System.out.println("Lista de Eventos del periodo "+ anio + "-" + periodo);
        listar().parallelStream()
            .filter(evento -> anio == evento.getFecha().getYear()
                && periodo.getMeses().contains(evento.getFecha().getMonth()))
            .forEach(e -> System.out.println(e.resumen()));

        System.out.println("=============================================");
        System.out.println("Lista de Eventos anteriores al periodo "+ anio + "-" + periodo);
        listar().parallelStream()
            .filter(evento -> evento.getFecha().isBefore(LocalDate.of(anio, Collections.min(periodo.getMeses()), 1)))
            .forEach(e -> System.out.println(e.resumen()));

        System.out.println("=============================================");
        System.out.println("Lista de Eventos posteriores al periodo "+ anio + "-" + periodo);
        listar().parallelStream()
            .filter(evento -> evento.getFecha().isAfter(
                LocalDate.of(anio, Collections.max(periodo.getMeses()).plus(1), 1).minusDays(1)))
            //.peek(_ -> System.out.println(LocalDate.of(anio, Collections.max(periodo.getMeses()).plus(1), 1).minusDays(1)))
            .forEach(e -> System.out.println(e.resumen()));
    }

    @Override
    public void iniciarCopiasDeSeguridad() {
        System.out.println("Inicia proceso de copias de seguridad");

        int cicloEjecucionSegundos = Integer.valueOf(Config.getProperty("ciclo-ejecucion", "10"));
        int tiempoEjecucionMinutos = Integer.valueOf(Config.getProperty("tiempo-ejecucion", "1"));

        var eventos = listar();
        System.out.println("Eventos " + eventos.size());
        CopiaSeguridad copiaSeguridad = new CopiaSeguridad(eventos);

        ejecutor.scheduleAtFixedRate(copiaSeguridad, 0, cicloEjecucionSegundos, TimeUnit.SECONDS);

        ejecutor.schedule(() -> {
            ejecutor.shutdown();
            try {
                if (!ejecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                    ejecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                ejecutor.shutdownNow();
            }
        }, tiempoEjecucionMinutos, TimeUnit.MINUTES);
    }
}
