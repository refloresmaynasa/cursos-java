package me.rflores.pruebas;

import me.rflores.modelo.daos.AlumnoDao;
import me.rflores.modelo.daos.impl.DaoFactory;
import me.rflores.modelo.entidades.Alumno;
import me.rflores.util.Util;

public class Ejercicio02 {
    public static void main(String[] args) {
        // Ejercicio Sesion 03 (26/06/2024)
        DaoFactory fabrica = DaoFactory.getInstance();
        AlumnoDao dao = fabrica.getAlumnoDao(Util.tipo);

        var alumno1 = new Alumno(1, "Carlos", "AP", 10, 20);
        var alumno2 = new Alumno(2, "Juan", "RE", 18, 21);
        var alumno3 = new Alumno(3, "Pedro", "RE", 15, 22);
        var alumno4 = new Alumno(4, "Luis", "AP", 20, 19);
        var alumno5 = new Alumno(5, "Jose", "AP", 5, 19);

        dao.create(alumno1);
        dao.create(alumno2);
        dao.create(alumno3);
        dao.create(alumno4);
        dao.create(alumno5);

        System.out.println("---------ORDENADOS POR NOMBRE---------------");
        for (Alumno a : dao.findAllOrderByNombre()) {
            System.out.println(a);
        }

        System.out.println("-----------ORDENADOS POR PROMEDIO-------------");
        for (Alumno a : dao.findAllOrderByPromedio()) {
            System.out.println(a);
        }

        System.out.println("------PROMEDIO TOTAL---------------");
        System.out.printf("Promedio Alumnos: %,6.2f %n", dao.getPromedioAlumnos());

        System.out.println("---------MIN(10) - MAX(18) PROMEDIO-----------");
        for (Alumno a : dao.findAllByPromedio(10, 18)) {
            System.out.println(a);
        }
    }
}
