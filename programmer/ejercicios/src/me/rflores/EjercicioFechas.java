package me.rflores;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EjercicioFechas {
    public static void main(String[] args) {

        var miNacimiento = LocalDate.of(1993, 2, 7);
        System.out.println("Mi fecha de nacimiento: " + miNacimiento);

        var miProximoCumple = miNacimiento.withYear(LocalDate.now().getYear() + 1);
        System.out.println("Mi proximo cumple: " + miProximoCumple);

        // Cuantos dias falta para mi cumpleaños
        var diasfaltantes = LocalDate.now().until(miProximoCumple, ChronoUnit.DAYS);
        System.out.println("Dias que falta para mi proximo cumple: " + diasfaltantes);

        // Vas a hacer una reunion 2 dias despues a las 8pm.
        var miReunion = miProximoCumple.plusDays(2).atTime(20, 0);
        System.out.println("Fecha y hora de mi reunion: " + miReunion);

        // Que dia de la semana naciste?
        System.out.println("El dia de la semana que naci es: " + miNacimiento.getDayOfWeek());
    }
}
