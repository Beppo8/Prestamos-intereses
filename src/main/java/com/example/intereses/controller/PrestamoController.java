package com.example.intereses.controller;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.service.PrestamoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        log.info("Obteniendo todos los préstamos");
        return prestamoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Prestamo> obtenerPorId(@PathVariable String id) {
        log.info("Obteniendo préstamo con ID: {}", id);

        return prestamoService.obtenerPorId(id);
    }

    @PostMapping
    public void crearPrestamo(@RequestBody Prestamo prestamo) {
        log.info("Creando préstamo: {}", prestamo);
        prestamoService.crearPrestamo(prestamo);
    }

    @PutMapping("/{id}/estado")
    public boolean actualizarEstado(@PathVariable String id, @RequestParam EstadoPrestamo estado) {
        log.info("Actualizando estado del préstamo {} a {}", id, estado);
        return prestamoService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarPrestamo(@PathVariable String id) {
        log.warn("Eliminando préstamo con ID: {}", id);
        return prestamoService.eliminarPrestamo(id);
    }

    @GetMapping("/{id}/calculo")
    public double calcularMontoTotal(@PathVariable String id,  @RequestParam TipoCliente tipoCliente) {
        log.info("Calculando monto total para préstamo {} con tipo de cliente {}", id, tipoCliente);
        return prestamoService.obtenerPorId(id)
                .map(prestamo -> prestamoService.calcularMontoTotal(prestamo, tipoCliente))
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));
    }


}
