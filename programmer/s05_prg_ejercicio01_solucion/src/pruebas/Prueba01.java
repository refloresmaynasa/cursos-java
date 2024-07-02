package pruebas;

import entidades.Asistente;
import entidades.Evento;
import service.EventoService;
import service.impl.EventoServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Prueba01 {
    public static void main(String ...args){

        EventoService servicio = new EventoServiceImpl();
        //TODO agregar tres eventos cada uno con 1 asistente.
        Asistente asistente1 = new Asistente(10,"juan");
        Asistente asistente2 = new Asistente(20,"maria");
        List<Asistente> lista = new ArrayList<>();
        lista.add(asistente1);
        lista.add(asistente2);
        Evento evento = new Evento(100,"JAVA DAY",lista);

        servicio.grabar(evento);
        //TODO mostrar los eventos con Streams

        servicio.listar().stream().forEach(System.out::println);

    }
}
