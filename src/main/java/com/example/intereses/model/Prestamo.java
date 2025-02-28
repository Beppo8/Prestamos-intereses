package com.example.intereses.model;

import java.time.LocalDate;

public record Prestamo(
        String id,
        double monto,
        String clienteId,
        LocalDate fecha,
        EstadoPrestamo estado
) {}
