package me.rflores.utiles;

import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Categoria;
import me.rflores.modelos.entidades.Evento;
import me.rflores.modelos.entidades.Expositor;
import me.rflores.servicios.impl.EventoServicioImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Util {
    public static void escribirArchivo(String rutaArchivo, List<String> lineas) {
        Path archivo = Paths.get(rutaArchivo);
        try {
            Files.createDirectories(archivo.getParent());
            Files.write(archivo, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error durante la escritura del archivo: " + e.getMessage());
        }
    }

    public static List<String> leerArchivo(String rutaArchivo) {
        Path archivo = Paths.get(rutaArchivo);
        try {
            if (Files.exists(archivo)) {
                return Files.readAllLines(archivo, StandardCharsets.UTF_8);
            } else {
                System.err.println("Archivo no existente: " + rutaArchivo);
                return null;
            }
        } catch (IOException e) {
            System.err.println("Error durante la lectura del archivo: " + e.getMessage());
            return null;
        }
    }

    public static void cargarDatos() {
        var servicio = new EventoServicioImpl();

        var expositor1 = new Expositor(101, "Claudia", "Rosell", 3000D, "example@example.com");
        var expositor2 = new Expositor(102, "Nicolasa", "Rosell", 2700D, "example@example.com");
        var expositor3 = new Expositor(103, "Ignacio", "Gasol", 2800D, "example@example.com");
        var expositor4 = new Expositor(104, "Micaela", "Zugaza", 3000D, "example@example.com");
        var expositor5 = new Expositor(105, "Tomás", "Bescansa", 3500D, "example@example.com");
        var expositor6 = new Expositor(106, "Agustín", "Rajoy", 4000D, "example@example.com");
        var expositor7 = new Expositor(107, "Pedro", "Domenech", 3500D, "example@example.com");

        var asistente1 = new Asistente(201, "Jamie", "Cain", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente2 = new Asistente(202, "Jamie", "Campbell", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente3 = new Asistente(203, "Aiden", "Pearce", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente4 = new Asistente(204, "Lane", "Hawkins", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente5 = new Asistente(205, "Rene", "Carpenter", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente6 = new Asistente(206, "Lane", "Slater", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente7 = new Asistente(207, "Willy", "Owen", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente8 = new Asistente(208, "Willy", "Riggs", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente9 = new Asistente(209, "Aiden", "Phelps", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente10 = new Asistente(210, "Rene", "Burton", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente11 = new Asistente(211, "Charlena", "Ehlinger", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente12 = new Asistente(212, "Yolanda", "Lymperopoulou", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente13 = new Asistente(213, "Ermina", "Mergel", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente14 = new Asistente(214, "Komon", "Ashi", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente15 = new Asistente(215, "Minabuchi", "Nanaru", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente16 = new Asistente(216, "Carely", "Hulbert", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente17 = new Asistente(217, "Carlicia", "Holtzinger", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente18 = new Asistente(218, "Ginette", "Corda", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente19 = new Asistente(219, "Ymelda", "Vecera", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");
        var asistente20 = new Asistente(220, "Poul", "Wagner", "correo@asistente.com", "+591 99999999", "av. asistente, direccion");

        var evento1 = new Evento.Builder().withId(1).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA 01").withTemporadaAlta(false).withCategoria(Categoria.SILVER).withExpositor(expositor1).withCapacidad(20)
            .withFecha(LocalDate.of(2024, 1, 7)).withDireccion("c. Calle 1 #123")
            .build();
        var evento2 = new Evento.Builder().withId(2).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA 02").withTemporadaAlta(false).withCategoria(Categoria.GOLD).withExpositor(expositor2).withCapacidad(20)
            .withFecha(LocalDate.of(2024, 1, 8)).withDireccion("c. Calle 1 #123")
            .build();
        var evento3 = new Evento.Builder().withId(3).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA 03").withTemporadaAlta(false).withCategoria(Categoria.GOLD).withExpositor(expositor3).withCapacidad(19)
            .withFecha(LocalDate.of(2024, 1, 9)).withDireccion("c. Calle 1 #123")
            .build();
        var evento4 = new Evento.Builder().withId(4).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA 04").withTemporadaAlta(false).withCategoria(Categoria.PLATINUM).withExpositor(expositor1).withCapacidad(20)
            .withFecha(LocalDate.of(2024, 1, 10)).withDireccion("c. Calle 1 #123")
            .build();
        var evento5 = new Evento.Builder().withId(5).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA EXPERT").withTemporadaAlta(false).withCategoria(Categoria.PLATINUM).withExpositor(expositor4).withCapacidad(40)
            .withFecha(LocalDate.of(2024, 3, 1)).withDireccion("c. Calle 1 #123")
            .build();

        var evento6 = new Evento.Builder().withId(6).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo(".NET 8").withTemporadaAlta(false).withCategoria(Categoria.SILVER).withExpositor(expositor7).withCapacidad(20)
            .withFecha(LocalDate.of(2024, 4, 1)).withDireccion("c. Calle 1 #123")
            .build();
        var evento7 = new Evento.Builder().withId(7).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo(".NET 8").withTemporadaAlta(true).withCategoria(Categoria.GOLD).withExpositor(expositor6).withCapacidad(25)
            .withFecha(LocalDate.of(2024, 4, 2)).withDireccion("c. Calle 2 #123")
            .build();
        var evento8 = new Evento.Builder().withId(8).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo(".NET 8").withTemporadaAlta(false).withCategoria(Categoria.GOLD).withExpositor(expositor6).withCapacidad(30)
            .withFecha(LocalDate.of(2024, 8, 5)).withDireccion("c. Calle 3 #123")
            .build();
        var evento9 = new Evento.Builder().withId(9).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA EXPERT").withTemporadaAlta(true).withCategoria(Categoria.PLATINUM).withExpositor(expositor5).withCapacidad(20)
            .withFecha(LocalDate.of(2024, 11, 4)).withDireccion("c. Calle 1 #123")
            .build();
        var evento10 = new Evento.Builder().withId(10).withHoraIngreso(LocalTime.of(8, 00)).withHoraSalida(LocalTime.of(12, 00))
            .withTitulo("JAVA EXPERT").withTemporadaAlta(true).withCategoria(Categoria.PLATINUM).withExpositor(expositor5).withCapacidad(100)
            .withFecha(LocalDate.of(2024, 12, 1)).withDireccion("c. Calle 1 #123")
            .build();

        servicio.grabar(evento1);
        servicio.grabar(evento2);
        servicio.grabar(evento3);
        servicio.grabar(evento4);
        servicio.grabar(evento5);
        servicio.grabar(evento6);
        servicio.grabar(evento7);
        servicio.grabar(evento8);
        servicio.grabar(evento9);
        servicio.grabar(evento10);

        servicio.agregarAsistente(evento1, asistente1);
        servicio.agregarAsistente(evento2, asistente2);
        servicio.agregarAsistente(evento2, asistente3);
        servicio.agregarAsistente(evento1, asistente4);
        servicio.agregarAsistente(evento3, asistente5);
        servicio.agregarAsistente(evento3, asistente6);
        servicio.agregarAsistente(evento4, asistente7);
        servicio.agregarAsistente(evento5, asistente8);
        servicio.agregarAsistente(evento5, asistente9);
        servicio.agregarAsistente(evento6, asistente10);
        servicio.agregarAsistente(evento6, asistente11);
        servicio.agregarAsistente(evento6, asistente12);
        servicio.agregarAsistente(evento7, asistente13);
        servicio.agregarAsistente(evento8, asistente14);
        servicio.agregarAsistente(evento9, asistente15);
        servicio.agregarAsistente(evento10, asistente16);
        servicio.agregarAsistente(evento10, asistente17);
        servicio.agregarAsistente(evento8, asistente18);
        servicio.agregarAsistente(evento5, asistente19);
        servicio.agregarAsistente(evento1, asistente20);
    }
}
