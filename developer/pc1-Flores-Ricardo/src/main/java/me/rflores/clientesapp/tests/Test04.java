package me.rflores.clientesapp.tests;

import me.rflores.clientesapp.services.impl.ClienteServiceImpl;
import me.rflores.clientesapp.utils.Filtro;

public class Test04 {
    public static void main(String[] args) {
        // 4. (3p) Buscar Clientes por: código, nombre o apellido. Usando Named Queries.
        var service = new ClienteServiceImpl();

        System.out.println("---- Filtro por Codigo ----");
        service.listarPorFiltro("6", Filtro.ByCodigo)
                .forEach(System.out::println);

        System.out.println("---- Filtro por Nombre ----");
        service.listarPorFiltro("ar", Filtro.ByNombre)
                .forEach(System.out::println);

        System.out.println("---- Filtro por Apellido ----");
        service.listarPorFiltro("ez", Filtro.ByApellido)
                .forEach(System.out::println);

    }
}
