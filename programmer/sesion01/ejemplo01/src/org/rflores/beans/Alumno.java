package org.rflores.beans;

import org.rflores.util.Util;

public class Alumno extends Persona {
    private double promedio;
    private String estado;

    public Alumno() {
    }

    public Alumno(double promedio, String nombre, boolean genero) {
        super(nombre, genero);
        this.promedio = promedio;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getEstado() {
        this.estado = (promedio >= Util.PROMEDIO) ? "aprobado" : "desaprobado";
        return estado;
    }
}
