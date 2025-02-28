package com.example.intereses.service;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.repository.PrestamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public List<Prestamo> obtenerTodos() {
        log.info("Servicio: Obteniendo todos los préstamos");
        return prestamoRepository.obtenerTodos();
    }

    public List<Prestamo> obtenerActivos() {
        log.info("Servicio: Obteniendo préstamos activos");
        return prestamoRepository.obtenerActivos();
    }

    public Optional<Prestamo> obtenerPorId(String id) {
        log.info("Servicio: Buscando préstamo con ID {}", id);
        return prestamoRepository.obtenerPorId(id);
    }

    public void crearPrestamo(Prestamo prestamo) {
        log.info("Servicio: Creando préstamo {}", prestamo);
        prestamoRepository.agregar(prestamo);
    }

    public boolean actualizarEstado(String id, EstadoPrestamo nuevoEstado) {
        log.info("Servicio: Actualizando estado del préstamo {} a {}", id, nuevoEstado);
        return prestamoRepository.actualizarEstado(id, nuevoEstado);
    }

    public boolean actualizarPrestamo(String id, EstadoPrestamo nuevoEstado) {
        return prestamoRepository.actualizarEstado(id, nuevoEstado);
    }

    public boolean eliminarPrestamo(String id) {
        log.warn("Servicio: Eliminando préstamo con ID {}", id);
        return prestamoRepository.eliminar(id);
    }

    public double calcularMontoTotal(Prestamo prestamo, TipoCliente tipoCliente) {
        double montoFinal = prestamo.monto() * (1 + tipoCliente.getTasaInteres());
        log.info("Servicio: Monto total para préstamo {} con cliente {} = {}", prestamo.id(), tipoCliente, montoFinal);
        return montoFinal;
    }


}
