package entidades;

import java.util.List;

public class Evento {
    private int id;
    private String nombre;
    private List<Asistente> asistentes;

    public Evento(int id, String nombre, List<Asistente> asistentes) {
        this.id = id;
        this.nombre = nombre;
        this.asistentes = asistentes;
    }

    public Evento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(List<Asistente> asistentes) {
        this.asistentes = asistentes;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", asistentes=" + asistentes +
                '}';
    }
}
