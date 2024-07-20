package me.rflores.servicios;

import me.rflores.modelos.entidades.Evento;
import me.rflores.utiles.Config;
import me.rflores.utiles.Util;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class CopiaSeguridad implements Runnable {
    private List<Evento> eventos;
    private final DateTimeFormatter fechaFormato = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public CopiaSeguridad(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public void run() {
        System.out.print("*");
        var rutaArchivo = Paths.get(Config.getProperty("ruta_copia_seguridad", "c:\\eventos"),
            "evento_" + LocalDateTime.now().format(fechaFormato) + ".bak").toString();

        var lineas = new LinkedList<String>();
        for (Evento evento : eventos) {
            StringBuilder linea = new StringBuilder();
            linea.append(evento.getId()).append(",");
            linea.append(evento.getCapacidad()).append(",");
            linea.append(evento.getFecha()).append(",");
            linea.append(evento.getTitulo()).append(",");
            linea.append(evento.getCategoria()).append(",");
            linea.append(evento.getHoraIngreso()).append(",");
            linea.append(evento.getHoraSalida()).append(",");
            linea.append(evento.getHoraSalida()).append(",");
            linea.append(evento.getDireccion()).append(",");
            lineas.add(linea.toString());
        }

        Util.escribirArchivo(rutaArchivo, lineas);
    }
}
