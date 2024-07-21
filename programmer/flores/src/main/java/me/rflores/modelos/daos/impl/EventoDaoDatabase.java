package me.rflores.modelos.daos.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import me.rflores.modelos.daos.EventoDao;
import me.rflores.modelos.entidades.Asistente;
import me.rflores.modelos.entidades.Categoria;
import me.rflores.modelos.entidades.Evento;
import me.rflores.modelos.entidades.Expositor;
import me.rflores.utiles.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDaoDatabase implements EventoDao {
    private ObjectMapper jsonMapper;

    private static final String URL = Config.getProperty("mysql_conexion", "jdbc:mysql://localhost:3306/evento");
    private static final String USER = Config.getProperty("mysql_user", "root");
    private static final String PASSWORD = Config.getProperty("mysql_password", "example");

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public EventoDaoDatabase() {
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.registerModule(new JavaTimeModule());
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public boolean agregarAsistente(Evento evento, Asistente asistente) {
        if (!existeEvento(evento)) {
            System.out.println("Evento no registrado!!!");
            return false;
        }

        if (loadEventos().stream().anyMatch(e -> e.asistenteYaAgregado(asistente))) {
            System.out.println("El asistente que desea registrar ya se encuentra registrado en otro Evento");
            return false;
        }

        var eventoEncontrado = find(evento.getId());
        eventoEncontrado.agregarAsistente(asistente);
        updateEvento(evento);
        return true;
    }

    @Override
    public void create(Evento evento) {
        if (!existeEvento(evento)) {
            if (loadEventos().parallelStream().anyMatch(e -> e.seTraslapa(evento))) {
                System.out.println("ERROR: Se encontro un evento traslapado, revise la fecha y horas del nuevo Evento");
                return;
            }

            try {
                saveEvento(evento);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("ERROR: Ya existe un Evento con los mismos datos");
        }
    }

    @Override
    public void update(Evento evento) {
        var eventoEncontrado = find(evento.getId());
        if (eventoEncontrado != null) {
            updateEvento(evento);
        }
    }

    @Override
    public void delete(Integer id) {
        var evento = find(id);
        if (evento != null) {
            deleteEvento(id);
        }
    }

    @Override
    public List<Evento> findAll() {
        return loadEventos();
    }

    @Override
    public Evento find(Integer id) {
        var eventos = loadEventos();
        return eventos.stream().filter(e -> e.getId() == id)
            .findFirst().orElse(null);
    }

    private boolean existeEvento(Evento evento) {
        return loadEventos().stream().anyMatch(e -> e.equals(evento));
    }

    private void saveEvento(Evento evento) throws JsonProcessingException {
        String sql = "INSERT INTO eventos (id, titulo, duracion, hora_ingreso, hora_salida, temporada_alta, categoria, expositor, asistentes, fecha, direccion, capacidad, costo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, evento.getId());
            pstmt.setString(2, evento.getTitulo());
            pstmt.setLong(3, evento.getDuracion().toMinutes());
            pstmt.setTime(4, Time.valueOf(evento.getHoraIngreso()));
            pstmt.setTime(5, Time.valueOf(evento.getHoraSalida()));
            pstmt.setBoolean(6, evento.isTemporadaAlta());
            pstmt.setString(7, evento.getCategoria().name());
            pstmt.setString(8, jsonMapper.writeValueAsString(evento.getExpositor()));
            pstmt.setString(9, jsonMapper.writeValueAsString(evento.getAsistentes()));
            pstmt.setDate(10, Date.valueOf(evento.getFecha()));
            pstmt.setString(11, evento.getDireccion());
            pstmt.setInt(12, evento.getCapacidad());
            pstmt.setDouble(13, evento.getCosto());
            pstmt.executeUpdate();
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private List<Evento> loadEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento evento = new Evento.Builder()
                    .withId(rs.getInt("id"))
                    .withTitulo(rs.getString("titulo"))
                    .withHoraIngreso(rs.getTime("hora_ingreso").toLocalTime())
                    .withHoraSalida(rs.getTime("hora_salida").toLocalTime())
                    .withTemporadaAlta(rs.getBoolean("temporada_alta"))
                    .withCategoria(Categoria.valueOf(rs.getString("categoria")))
                    .withExpositor(jsonMapper.readValue(rs.getString("expositor"), Expositor.class))
                    .withFecha(rs.getDate("fecha").toLocalDate())
                    .withDireccion(rs.getString("direccion"))
                    .withCapacidad(rs.getInt("capacidad"))
                    .build();
                var asistentes = (List<Asistente>) jsonMapper.readValue(rs.getString("asistentes"), jsonMapper.getTypeFactory().constructCollectionType(List.class, Asistente.class));
                asistentes.forEach(evento::agregarAsistente);
                eventos.add(evento);
            }
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    private boolean updateEvento(Evento evento) {
        String sql = "UPDATE eventos SET titulo = ?, duracion = ?, hora_ingreso = ?, hora_salida = ?, " +
            "temporada_alta = ?, categoria = ?, expositor = ?, asistentes = ?, fecha = ?, " +
            "direccion = ?, capacidad = ?, costo = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, evento.getTitulo());
            pstmt.setLong(2, evento.getDuracion().toMinutes());
            pstmt.setTime(3, Time.valueOf(evento.getHoraIngreso()));
            pstmt.setTime(4, Time.valueOf(evento.getHoraSalida()));
            pstmt.setBoolean(5, evento.isTemporadaAlta());
            pstmt.setString(6, evento.getCategoria().name());
            pstmt.setString(7, jsonMapper.writeValueAsString(evento.getExpositor()));
            pstmt.setString(8, jsonMapper.writeValueAsString(evento.getAsistentes()));
            pstmt.setDate(9, Date.valueOf(evento.getFecha()));
            pstmt.setString(10, evento.getDireccion());
            pstmt.setInt(11, evento.getCapacidad());
            pstmt.setDouble(12, evento.getCosto());
            pstmt.setInt(13, evento.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean deleteEvento(int eventoId) {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, eventoId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
