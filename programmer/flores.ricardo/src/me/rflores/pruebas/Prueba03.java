package me.rflores.pruebas;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;
import me.rflores.modelo.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Ubicacion;

import java.time.LocalTime;

public class Prueba03 {
    /**
     * Genera una clase Prueba03 que muestre la lista de eventos y el total recaudado por cada evento.
     */
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
        var asistente2 = new Asistente.Builder()
                .codigo(2)
                .apellidos("Perez")
                .nombre("Juan2")
                .correo("juan2@perez.com")
                .direccion("c. Calle1 #111")
                .telefono("+51 99999999")
                .build();
        var asistente3 = new Asistente.Builder()
                .codigo(3)
                .apellidos("Perez")
                .nombre("Juan3")
                .correo("juan3@perez.com")
                .direccion("c. Calle1 #111")
                .telefono("+51 99999999")
                .build();
        var asistente4 = new Asistente.Builder()
                .codigo(4)
                .apellidos("Perez")
                .nombre("Juan4")
                .correo("juan4@perez.com")
                .direccion("c. Calle1 #111")
                .telefono("+51 99999999")
                .build();

        var asistente5 = new Asistente.Builder()
                .codigo(5)
                .apellidos("Camacho")
                .nombre("Jorge")
                .correo("jorge4@camacho.com")
                .direccion("c. Calle1 #111")
                .telefono("+51 99999999")
                .build();

        var expositor1 = new Expositor.Builder()
                .codigo(1)
                .apellidos("Castro")
                .correo("jose@castro.com")
                .nombre("Jose")
                .sueldo(200.50D)
                .build();
        var expositor2 = new Expositor.Builder()
                .codigo(2)
                .apellidos("Eduardo")
                .correo("robert@eduardo.com")
                .nombre("Robert")
                .sueldo(300.00D)
                .build();

        var evento1 = new Evento.Builder()
                .codigo(1)
                .horaIngreso(LocalTime.of(8, 30))
                .horaSalida(LocalTime.of(13, 0))
                .titulo("HTML-CSS 001")
                .temporadaAlta(true)
                .ubicacion(Ubicacion.VIP)
                .expositor(expositor1)
                .build();

        var evento2 = new Evento.Builder()
                .codigo(2)
                .horaIngreso(LocalTime.of(9, 00))
                .horaSalida(LocalTime.of(13, 00))
                .titulo("EVENTO 002")
                .temporadaAlta(false)
                .ubicacion(Ubicacion.GOLD)
                .expositor(expositor1)
                .build();

        var evento3 = new Evento.Builder()
                .codigo(3)
                .horaIngreso(LocalTime.of(9, 00))
                .horaSalida(LocalTime.of(13, 00))
                .titulo("NODE JS 003")
                .temporadaAlta(false)
                .ubicacion(Ubicacion.PLATINUM)
                .expositor(expositor1)
                .build();

        var evento4 = new Evento.Builder()
                .codigo(4)
                .horaIngreso(LocalTime.of(13, 00))
                .horaSalida(LocalTime.of(18, 45))
                .titulo("JAVA PROGRAMMER 004")
                .temporadaAlta(true)
                .ubicacion(Ubicacion.GOLD)
                .expositor(expositor2)
                .build();

        servicio.grabar(evento1);
        servicio.grabar(evento2);
        servicio.grabar(evento3);
        servicio.grabar(evento4);

        servicio.agregarAsistente(evento1, asistente1);
        servicio.agregarAsistente(evento2, asistente2);
        servicio.agregarAsistente(evento2, asistente3);
        servicio.agregarAsistente(evento3, asistente4);
        servicio.agregarAsistente(evento4, asistente5);

        System.out.println("========== RESULTADO ==========");

        for (var evento : servicio.listar()) {
            System.out.println("--------------------------------");
            System.out.printf("Evento: %s %s - #Asistentes: %d %n -> Costo Ingreso: %,6.2f %n -> TOTAL RECAUDADO: %,6.2f%n",
                    evento.getCodigo(), evento.getTitulo(), evento.getAsistentes().size(), evento.obtenerCostoIngreso(),
                    evento.calcularTotalRecaudado());
        }
    }
}
