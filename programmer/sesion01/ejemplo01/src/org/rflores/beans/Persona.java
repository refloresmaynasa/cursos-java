package org.rflores.beans;

import org.rflores.util.Util;

public class Persona {
    private int id;
    private String nombre;
    private boolean genero;

    public Persona(){
        this.id =  Util.generarId();
    }

    public Persona(String nombre, boolean genero) {
        this.id =  Util.generarId();
        this.nombre = nombre;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
}
