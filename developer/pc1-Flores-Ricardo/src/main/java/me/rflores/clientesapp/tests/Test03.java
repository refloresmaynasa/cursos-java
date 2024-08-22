package me.rflores.clientesapp.tests;

import me.rflores.clientesapp.services.impl.ClienteServiceImpl;

public class Test03 {
    public static void main(String[] args) {
        // 3. (4p) Actualizar los datos de un registro de la tabla clientes mediante HQL.
        var service = new ClienteServiceImpl();

        var cliente = service.buscar(2);
        System.out.println(cliente);
        cliente.setNombre("Roberto");
        cliente.setApellido("Carlos");
        cliente.setTotalCompras(3500D);

        service.actualizar(cliente);

        service.listar().forEach(System.out::println);
    }
}
