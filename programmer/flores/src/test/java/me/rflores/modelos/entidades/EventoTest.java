package me.rflores.modelos.entidades;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoTest extends TestCase {

    public void testSeTraslapa() {
        var evento = new Evento.Builder().withTitulo("Evento").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(8, 0)).withHoraSalida(LocalTime.of(9,0)).build();
        var evento1 = new Evento.Builder().withTitulo("Evento 1").withFecha(LocalDate.of(2024, 1,2))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(8, 0)).withHoraSalida(LocalTime.of(9,0)).build();

        var evento2 = new Evento.Builder().withTitulo("Evento 2").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(8, 30)).withHoraSalida(LocalTime.of(9,30)).build();
        var evento3 = new Evento.Builder().withTitulo("Evento 3").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(7, 0)).withHoraSalida(LocalTime.of(10,0)).build();
        var evento4 = new Evento.Builder().withTitulo("Evento 1").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(7, 1)).withHoraSalida(LocalTime.of(8,1)).build();

        var evento5 = new Evento.Builder().withTitulo("Evento 5").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(7, 0)).withHoraSalida(LocalTime.of(8,0)).build();
        var evento6 = new Evento.Builder().withTitulo("Evento 6").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(9, 0)).withHoraSalida(LocalTime.of(10,0)).build();
        var evento7 = new Evento.Builder().withTitulo("Evento 7").withFecha(LocalDate.of(2024, 1,1))
            .withCategoria(Categoria.PLATINUM)
            .withHoraIngreso(LocalTime.of(8, 0)).withHoraSalida(LocalTime.of(9,0)).build();

        assertFalse(evento1.getTitulo(), evento.seTraslapa(evento1));
        assertTrue(evento2.getTitulo(), evento.seTraslapa(evento2));
        assertTrue(evento3.getTitulo(), evento.seTraslapa(evento3));
        assertTrue(evento4.getTitulo(), evento.seTraslapa(evento4));
        assertFalse(evento5.getTitulo(), evento.seTraslapa(evento5));
        assertFalse(evento6.getTitulo(), evento.seTraslapa(evento6));
        assertTrue(evento7.getTitulo(), evento.seTraslapa(evento7));
    }
}