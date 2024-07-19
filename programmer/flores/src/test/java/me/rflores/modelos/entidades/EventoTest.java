package me.rflores.modelos.entidades;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoTest extends TestCase {

    public void testSeTraslapa() {
        var evento = new Evento.Builder().titulo("Evento").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(8, 0)).horaSalida(LocalTime.of(9,0)).build();
        var evento1 = new Evento.Builder().titulo("Evento 1").fecha(LocalDate.of(2024, 1,2))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(8, 0)).horaSalida(LocalTime.of(9,0)).build();

        var evento2 = new Evento.Builder().titulo("Evento 2").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(8, 30)).horaSalida(LocalTime.of(9,30)).build();
        var evento3 = new Evento.Builder().titulo("Evento 3").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(7, 0)).horaSalida(LocalTime.of(10,0)).build();
        var evento4 = new Evento.Builder().titulo("Evento 1").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(7, 1)).horaSalida(LocalTime.of(8,1)).build();

        var evento5 = new Evento.Builder().titulo("Evento 5").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(7, 0)).horaSalida(LocalTime.of(8,0)).build();
        var evento6 = new Evento.Builder().titulo("Evento 6").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(9, 0)).horaSalida(LocalTime.of(10,0)).build();
        var evento7 = new Evento.Builder().titulo("Evento 7").fecha(LocalDate.of(2024, 1,1))
            .categoria(Categoria.PLATINUM)
            .horaIngreso(LocalTime.of(8, 0)).horaSalida(LocalTime.of(9,0)).build();

        assertFalse(evento1.getTitulo(), evento.seTraslapa(evento1));
        assertTrue(evento2.getTitulo(), evento.seTraslapa(evento2));
        assertTrue(evento3.getTitulo(), evento.seTraslapa(evento3));
        assertTrue(evento4.getTitulo(), evento.seTraslapa(evento4));
        assertFalse(evento5.getTitulo(), evento.seTraslapa(evento5));
        assertFalse(evento6.getTitulo(), evento.seTraslapa(evento6));
        assertTrue(evento7.getTitulo(), evento.seTraslapa(evento7));
    }
}