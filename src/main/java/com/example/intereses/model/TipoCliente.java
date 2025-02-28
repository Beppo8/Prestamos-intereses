package com.example.intereses.model;

public enum TipoCliente {
    REGULAR(0.10),  // 10%
    VIP(0.05); // 5%

    private final double tasaInteres;

    TipoCliente(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }
}
