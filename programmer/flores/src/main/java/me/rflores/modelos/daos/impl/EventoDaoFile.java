package me.rflores.modelos.daos.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.rflores.modelos.daos.EventoDao;
import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Evento;
import me.rflores.utiles.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventoDaoFile implements EventoDao {
    private List<Evento> eventos;
    private String rutaArchivo;
    private ObjectMapper jsonMapper;

    public EventoDaoFile() {
        this.eventos = new ArrayList<>();
        this.rutaArchivo = Config.getProperty("file_dao", "c:\\eventos.json");
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.registerModule(new JavaTimeModule());
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        eventos = getAllFromFile();
        if (!existeEvento(evento)) {
            System.out.println("Evento no registrado!!!");
            return false;
        }

        if (this.eventos.stream().anyMatch(e -> e.asistenteYaAgregado(asistente))) {
            System.out.println("El asistente que desea registrar ya se encuentra registrado en otro Evento");
            return false;
        }

        var eventoEncontrado = find(evento.getId());
        eventoEncontrado.agregarAsistente(asistente);

        saveEventosToFile(this.eventos);
        return true;
    }

    @Override
    public void create(Evento evento) {
        eventos = getAllFromFile();
        if (!existeEvento(evento)) {
            if (eventos.parallelStream().anyMatch(e -> e.seTraslapa(evento))) {
                System.out.println("ERROR: Se encontro un evento traslapado, revise la fecha y horas del nuevo Evento");
                return;
            }

            eventos.add(evento);
        } else {
            System.out.println("ERROR: Ya existe un Evento con los mismos datos");
        }

        saveEventosToFile(eventos);
    }

    @Override
    public void update(Evento evento) {
        eventos = getAllFromFile();
        var eventoEncontrado = find(evento.getId());
        if (eventoEncontrado != null) {
            var index = eventos.indexOf(eventoEncontrado);
            eventos.set(index, evento);
        }
        saveEventosToFile(eventos);
    }

    @Override
    public void delete(Integer id) {
        eventos = getAllFromFile();
        var evento = find(id);
        if (evento != null) {
            eventos.remove(evento);
        }
        saveEventosToFile(eventos);
    }

    @Override
    public List<Evento> findAll() {
        eventos = getAllFromFile();
        return eventos;
    }

    @Override
    public Evento find(Integer id) {
        eventos = getAllFromFile();
        return this.eventos.stream().filter(e -> e.getId() == id)
            .findFirst().orElse(null);
    }

    private boolean existeEvento(Evento evento) {
        return this.eventos.stream().anyMatch(e -> e.equals(evento));
    }

    private void saveEventosToFile(List<Evento> eventos) {
        try {
            jsonMapper.writeValue(new File(rutaArchivo), eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Evento> getAllFromFile() {
        List<Evento> eventoFile = new ArrayList<>();
        try {
            File file = new File(rutaArchivo);
            if (file.exists()) {
                eventoFile = jsonMapper.readValue(file, new TypeReference<>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventoFile;
    }

    private void clearFile() {
        try {
            new FileOutputStream(rutaArchivo).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
