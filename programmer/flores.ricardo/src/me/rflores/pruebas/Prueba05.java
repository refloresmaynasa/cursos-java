package me.rflores.pruebas;

import me.rflores.modelo.entidades.Asistente;
import me.rflores.modelo.entidades.Evento;
import me.rflores.modelo.entidades.Expositor;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Ubicacion;

import java.time.LocalTime;
import java.util.stream.Collectors;

public class Prueba05 {
    /**
     * Genera una clase Prueba05 que muestre el sueldo a apagar a todos los expositores que dieron charlas en VIP.
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
        var expositor3 = new Expositor.Builder()
                .codigo(3)
                .apellidos("Alberto")
                .correo("albertot@eduardo.com")
                .nombre("Robert")
                .sueldo(700.00D)
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

        var evento5 = new Evento.Builder()
                .codigo(5)
                .horaIngreso(LocalTime.of(13, 00))
                .horaSalida(LocalTime.of(18, 45))
                .titulo("JAVA PROGRAMMER 005")
                .temporadaAlta(true)
                .ubicacion(Ubicacion.VIP)
                .expositor(expositor3)
                .build();

        var evento6 = new Evento.Builder()
                .codigo(6)
                .horaIngreso(LocalTime.of(13, 00))
                .horaSalida(LocalTime.of(18, 45))
                .titulo("JAVA PROGRAMMER 006")
                .temporadaAlta(true)
                .ubicacion(Ubicacion.VIP)
                .expositor(expositor3)
                .build();

        servicio.grabar(evento1);
        servicio.grabar(evento2);
        servicio.grabar(evento3);
        servicio.grabar(evento4);
        servicio.grabar(evento5);
        servicio.grabar(evento6);

        servicio.agregarAsistente(evento1, asistente1);
        servicio.agregarAsistente(evento2, asistente2);
        servicio.agregarAsistente(evento2, asistente3);
        servicio.agregarAsistente(evento3, asistente4);
        servicio.agregarAsistente(evento4, asistente5);

        System.out.println("========== RESULTADO ==========");

        var ubicacionFiltro = Ubicacion.VIP;

        servicio.listar().stream()
                .filter(e -> e.getUbicacion().equals(ubicacionFiltro))
                .map(Evento::getExpositor)
                .collect(Collectors.groupingBy(Expositor::getCodigo, Collectors.toList()))
                .forEach((codigo, expositores) -> {
                    System.out.println("----------------------");
                    System.out.println(expositores.getFirst());
                    var total = expositores.stream().mapToDouble(Expositor::getSueldo)
                            .sum();
                    System.out.printf("Total Eventos: %s %n", expositores.size());
                    System.out.printf("TOTAL GANADO: %,6.2f %n", total);
                });
    }
}
