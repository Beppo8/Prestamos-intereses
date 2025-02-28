package com.example.intereses.repository;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.EstadoPrestamo;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PrestamoRepository {

    private final List<Prestamo>  prestamos =  new ArrayList<>();

    public List<Prestamo> obtenerTodos() {
        return prestamos;
    }

    public Optional<Prestamo> obtenerPrestamo(String id) {
        return prestamos.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    public void agregar(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public boolean actualizarEstado(String id, EstadoPrestamo nuevoEstado) {
        return prestamos.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .map(p -> {
                    prestamos.remove(p);
                    prestamos.add(new Prestamo(p.id(), p.monto(), p.clienteId(), p.fecha(), nuevoEstado));
                    return true;
                }).orElse(false);
    }


    public boolean eliminar(String id) {
        return prestamos.removeIf(p -> p.id().equals(id));
    }

    public List<Prestamo> obtenerActivos() {
        return prestamos.stream()
                .filter(p -> p.estado() == EstadoPrestamo.PENDIENTE)
                .collect(Collectors.toList());
    }
}
