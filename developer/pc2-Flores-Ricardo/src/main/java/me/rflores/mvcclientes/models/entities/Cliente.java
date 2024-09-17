package me.rflores.mvcclientes.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 20, message = "El apellido no debe tener mas de 20 caracteres")
    private String apellido;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 20, message = "El nombre no debe tener mas de 20 caracteres")
    private String nombre;

    @Email(message = "Correo electrónico no válido")
    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "\\d{7,10}", message = "El teléfono debe tener entre 7 y 10 dígitos")
    private String telefono;

    public Cliente() {
    }

    public Cliente(int id, String apellido, String nombre, String correo, String telefono) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
