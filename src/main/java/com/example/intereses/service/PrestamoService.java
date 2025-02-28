package com.example.intereses.service;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.obtenerTodos();
    }

    public List<Prestamo> obtenerActivos() {
        return prestamoRepository.obtenerActivos();
    }

    public void crearPrestamo(Prestamo prestamo) {
        prestamoRepository.agregar(prestamo);
    }

    public boolean actualizarPrestamo(Prestamo prestamo) {
        return prestamoRepository.actualizarEstado(id, nuevoEstado);
    }

    public boolean eliminarPrestamo(String id) {
        return prestamoRepository.eliminar(id);
    }

    public double calcularMontoTotal(Prestamo prestamo, TipoCliente tipoCliente) {
        return prestamo.monto() * (1 + tipoCliente.getTasaInteres());
    }


}
