package me.rflores.sistemaventas.servicios;

import me.rflores.sistemaventas.modelos.entidades.Cliente;

import java.util.List;

public interface ClienteServicio {
    public List<Cliente> listar();

    public List<Cliente> listarPorNombre(String nombre);

    public List<Cliente> listarPorApellido(String apellido);

    public void grabar(Cliente cliente);

    public void actualizar(Cliente cliente);

    public void eliminar(int id);

    public Cliente buscar(int codigo);
}
