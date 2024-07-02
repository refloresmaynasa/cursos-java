package entidades;

public class Asistente {
    private int id;
    private String nombre;

    public Asistente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Asistente() {
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

    @Override
    public String toString() {
        return "Asistente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
