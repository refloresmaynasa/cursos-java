package me.rflores.clientesapp.tests;

import me.rflores.clientesapp.services.impl.ClienteServiceImpl;

public class Test05 {
    public static void main(String[] args) {
        // 5. (5p) Desarrollo los mapeos necesarios para hacer una consulta usando Criteria Query.
        // Lista de Clientes con compras mayor a:
        var service = new ClienteServiceImpl();

        System.out.println("---- Clientes con total de compras mayor a: 50000 ----");
        service.listarPorMayoresCompras(50000D)
                .forEach(System.out::println);
    }
}
