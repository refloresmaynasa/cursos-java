package me.rflores.clientesapp.tests;

import me.rflores.clientesapp.services.impl.ClienteServiceImpl;

public class Test01 {
    public static void main(String[] args) {
        // 1. (5p) Consulta de todos los clientes, use lista de clientes mediante HQL.
        var service = new ClienteServiceImpl();
        var clientes = service.listar();

        clientes.forEach(System.out::println);
    }
}
