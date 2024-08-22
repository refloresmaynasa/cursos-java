package me.rflores.clientesapp.tests;

import me.rflores.clientesapp.services.impl.ClienteServiceImpl;

public class Test02 {
    public static void main(String[] args) {
        // 2. (3p) Eliminar clientes por código. Usando consultas por código con HQL.
        var service = new ClienteServiceImpl();

        service.listar().forEach(System.out::println);

        service.eliminar(1);

        service.listar().forEach(System.out::println);
    }
}
