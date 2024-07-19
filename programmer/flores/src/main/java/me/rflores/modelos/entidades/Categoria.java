package me.rflores.modelos.entidades;

public enum Categoria {
    PLATINUM(500D),
    GOLD(350D),
    SILVER(100D);

    private double costo;

    private Categoria(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }
}
