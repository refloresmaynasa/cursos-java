package me.rflores.clientesapp.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@NamedQueries({
        @NamedQuery(name = "Cliente.findByCodigo", query = "FROM Cliente WHERE codigo = :codigo"),
        @NamedQuery(name = "Cliente.findByNombre", query = "FROM Cliente WHERE nombre LIKE :nombre"),
        @NamedQuery(name = "Cliente.findByApellido", query = "FROM Cliente WHERE apellido LIKE :apellido")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private double totalCompras;
    private LocalDate fechaRegistro;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, String apellido, String telefono, String email, double totalCompras) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.totalCompras = totalCompras;

        this.fechaRegistro = LocalDate.now();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(double totalCompras) {
        this.totalCompras = totalCompras;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", totalCompras=" + totalCompras +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
