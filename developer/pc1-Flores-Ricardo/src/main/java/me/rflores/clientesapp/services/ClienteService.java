package me.rflores.clientesapp.services;

import me.rflores.clientesapp.domain.entities.Cliente;
import me.rflores.clientesapp.utils.Filtro;

import java.util.List;

public interface ClienteService {
    public List<Cliente> listar();

    public List<Cliente> listarPorFiltro(String valor, Filtro filtro);

    public void grabar(Cliente cliente);

    public void actualizar(Cliente cliente);

    public void eliminar(int id);

    public Cliente buscar(int codigo);

    public List<Cliente> listarPorMayoresCompras(double compraMinima);
}
