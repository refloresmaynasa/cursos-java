package me.rflores.pruebas;

import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Util;

public class CopiaDeSeguridad {
    private static EventoServicio servicio = new EventoServicioImpl();

    public static void main(String[] args) {
        Util.cargarDatos();
        ejecutarCopiaDeSeguridad();
    }

    public static void ejecutarCopiaDeSeguridad(){
        System.out.println("\n====== Prueba de Copia de Seguridad ======");
        servicio.iniciarCopiasDeSeguridad();
    }
}
