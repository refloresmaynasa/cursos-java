package me.rflores.modelo.entidades;

public class Asistente {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;

    private Asistente(Builder builder) {
        this.codigo = builder.codigo;
        this.nombre = builder.nombre;
        this.apellidos = builder.apellidos;
        this.correo = builder.correo;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
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

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Asistente asistente)) {
            return false;
        }

        return asistente.codigo == this.codigo;
    }

    @Override
    public String toString() {
        return "Asistente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }

    public static class Builder {
        private int codigo;
        private String nombre;
        private String apellidos;
        private String correo;
        private String telefono;
        private String direccion;

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

        public Builder correo(String correo) {
            this.correo = correo;
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Asistente build() {
            return new Asistente(this);
        }
    }
}
