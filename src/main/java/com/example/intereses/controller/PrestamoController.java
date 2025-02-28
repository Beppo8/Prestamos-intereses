package com.example.intereses.controller;

import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Prestamo> obtenerPorId(@PathVariable String id) {
        return prestamoService.obtenerPorId(id);
    }

    @PostMapping
    public void crearPrestamo(@RequestBody Prestamo prestamo) {
        prestamoService.crearPrestamo(prestamo);
    }

    @PutMapping("/{id}/estado")
    public boolean actualizarEstado(@PathVariable String id, @RequestParam EstadoPrestamo estado) {
        return prestamoService.actualizarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarPrestamo(@PathVariable String id) {
        return prestamoService.eliminarPrestamo(id);
    }

    @GetMapping("/{id}/calculo")
    public double calcularMontoTotal(@PathVariable String id,  @RequestParam TipoCliente tipoCliente) {
        return prestamoService.obtenerPorId(id)
                .map(prestamo -> prestamoService.calcularMontoTotal(prestamo, tipoCliente))
                .orElseThrow(() -> new RuntimeException("Prestamo no encontrado"));
    }


}
