package com.example.intereses.controller;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/prestamos")
@Tag(name = "Préstamos", description = "API para gestionar préstamos de clientes")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los préstamos", description = "Devuelve la lista de todos los préstamos disponibles.")
    public List<Prestamo> obtenerTodos() {
        log.info("Obteniendo todos los préstamos");
        return prestamoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un préstamo por ID", description = "Busca un préstamo por su ID.")
    public Optional<Prestamo> obtenerPorId(@PathVariable String id) {
        log.info("Obteniendo préstamo con ID: {}", id);

        return prestamoService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo préstamo", description = "Agrega un nuevo préstamo al sistema.")
    public void crearPrestamo(@RequestBody Prestamo prestamo) {
        log.info("Creando préstamo: {}", prestamo);
        prestamoService.crearPrestamo(prestamo);
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de un préstamo", description = "Cambia el estado de un préstamo entre PENDIENTE y PAGADO.")
    public boolean actualizarEstado(@PathVariable String id, @RequestParam EstadoPrestamo estado) {
        log.info("Actualizando estado del préstamo {} a {}", id, estado);
        return prestamoService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un préstamo", description = "Elimina un préstamo por ID.")
    public boolean eliminarPrestamo(@PathVariable String id) {
        log.warn("Eliminando préstamo con ID: {}", id);
        return prestamoService.eliminarPrestamo(id);
    }

    @GetMapping("/{id}/calculo")
    @Operation(summary = "Calcular monto total con interés", description = "Calcula el monto total a pagar con la tasa de interés según el tipo de cliente.")
    public double calcularMontoTotal(@PathVariable String id,  @RequestParam TipoCliente tipoCliente) {
        log.info("Calculando monto total para préstamo {} con tipo de cliente {}", id, tipoCliente);
        return prestamoService.obtenerPorId(id)
                .map(prestamo -> prestamoService.calcularMontoTotal(prestamo, tipoCliente))
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));
    }


}
