package me.rflores;

import me.rflores.pruebas.CrudEventos;
import me.rflores.pruebas.EventoTraslapado;
import me.rflores.servicios.EventoServicio;
import me.rflores.servicios.impl.EventoServicioImpl;
import me.rflores.utiles.Util;

import java.util.Scanner;

public class App {
    private static EventoServicio servicio = new EventoServicioImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Util.cargarDatos();
        boolean exit = false;

        while (!exit) {
            mostrarMenu();
            int choice = recuperarOpcion();
            exit = ejecutarOpcion(choice);
        }

        System.out.println("Thank you for using the application. Goodbye!");
    }

    private static void mostrarMenu() {
        System.out.println("\n---- Menu de Operaciones ----");
        System.out.println("1. Prueba CRUD de eventos y validacion que no se puede traslapar eventos");
        System.out.println("2. Option 2");
        System.out.println("3. Option 3");
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

    private static boolean ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crudYNoTraslapar();
                return false;
            case 2:
                option2();
                return false;
            case 3:
                option3();
                return false;
            case 0:
                return true;
            default:
                System.out.println("Opcion incorrecta. Por favor pruebe nuevamente.");
                return false;
        }
    }

    private static void crudYNoTraslapar() {
        CrudEventos.ejecutarCrud();
        EventoTraslapado.ejecutarPruebaTraslapado();
    }

    private static void option2() {
        System.out.println("You selected Option 2.");
        // Add your code for Option 2 here
    }

    private static void option3() {
        System.out.println("You selected Option 3.");
        // Add your code for Option 3 here
    }
}
