package me.rflores;

import me.rflores.pruebas.*;
import me.rflores.utiles.Util;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Util.cargarDatos();
        boolean exit = false;

        while (!exit) {
            mostrarMenu();
            int choice = recuperarOpcion();
            exit = ejecutarOpcion(choice);
        }

        System.out.println("Gracias por usar esta aplicacion");
    }

    private static void mostrarMenu() {
        System.out.println("\n---- Menu de Operaciones ----");
        System.out.println("1. Prueba CRUD de eventos y validacion que no se puede traslapar eventos");
        System.out.println("2. Crear eventos para varios periodos y mostrarlos por periodo");
        System.out.println("3. Generacion de copias de seguridad");
        System.out.println("4. Reportes");
        System.out.println("0. Exit");
        System.out.print("Seleccione una opcion: ");
    }

    private static int recuperarOpcion() {
        while (!scanner.hasNextInt()) {
            System.out.print("Valor incorrecto. Por favor seleccion una opcion: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean ejecutarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                crudYNoTraslapar();
                return false;
            case 2:
                crearMostrarPorPeriodos();
                return false;
            case 3:
                generarCopiaSeguridad();
                return false;
            case 4:
                generarReportes();
                return false;
            case 0:
                return true;
            default:
                System.out.println("Opcion incorrecta. Por favor pruebe nuevamente.");
                return false;
        }
    }

    private static void generarReportes() throws Exception {
        Reportes.generarReportes();
    }

    private static void generarCopiaSeguridad() {
        CopiaDeSeguridad.ejecutarCopiaDeSeguridad();
    }

    private static void crearMostrarPorPeriodos() {
        EventosPorPeriodos.ejecutarEventosPorPeriodos();
    }

    private static void crudYNoTraslapar() {
        CrudEventos.ejecutarCrud();
        EventoTraslapado.ejecutarPruebaTraslapado();
    }
}
