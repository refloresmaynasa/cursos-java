package me.rflores.modelo.entidades;

public class Expositor {
    private int codigo;
    private String nombre;
    private String apellidos;
    private double sueldo;
    private String correo;

    private Expositor(Builder builder) {
        this.codigo = builder.codigo;
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.sueldo = builder.sueldo;
        this.correo = builder.correo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Expositor{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    public static class Builder {
        private int codigo;
        private String nombre;
        private String apellidos;
        private double sueldo;
        private String correo;

        public Builder codigo(int codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public Builder sueldo(double sueldo) {
            this.sueldo = sueldo;
            return this;
        }

        public Builder correo(String correo) {
            this.correo = correo;
            return this;
        }

        public Expositor build() {
            return new Expositor(this);
        }
    }
}
