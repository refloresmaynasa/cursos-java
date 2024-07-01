package me.rflores.util;

public enum Ubicacion {
    PLATINUM('P', 250.50D),
    GOLD('G', 150.50D),
    VIP('V', 95.00D);

    private char codigo;
    private double costo;

    private Ubicacion(char codigo, double costo) {
        this.codigo = codigo;
        this.costo = costo;
    }

    public char getCodigo() {
        return codigo;
    }

    public double getCosto() {
        return costo;
    }
}
