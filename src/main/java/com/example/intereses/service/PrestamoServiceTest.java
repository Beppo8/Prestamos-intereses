package com.example.intereses.service;

import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.model.Prestamo;
import com.example.intereses.model.TipoCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrestamoServiceTest {

    @InjectMocks
    private PrestamoService prestamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearPrestamo_DeberiaAgregarPrestamo() {
        Prestamo prestamo = new Prestamo("1", 1000.0, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);

        prestamoService.crearPrestamo(prestamo);

        List<Prestamo> prestamos = prestamoService.obtenerTodos();
        assertTrue(prestamos.contains(prestamo), "El préstamo debería haber sido agregado.");
    }

    @Test
    void obtenerActivos_DeberiaRetornarSoloPrestamosPendientes() {
        Prestamo p1 = new Prestamo("1", 500, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        Prestamo p2 = new Prestamo("2", 1000, "C2", LocalDate.now(), EstadoPrestamo.PAGADO);

        prestamoService.crearPrestamo(p1);
        prestamoService.crearPrestamo(p2);

        List<Prestamo> activos = prestamoService.obtenerActivos();

        assertEquals(1, activos.size());
        assertEquals("1", activos.get(0).id());
    }

    @Test
    void actualizarEstado_DeberiaActualizarElEstadoDelPrestamo() {
        Prestamo prestamo = new Prestamo("1", 800, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        prestamoService.crearPrestamo(prestamo);

        boolean actualizado = prestamoService.actualizarEstado("1", EstadoPrestamo.PAGADO);

        assertTrue(actualizado, "El préstamo debería actualizarse.");
        Optional<Prestamo> actualizadoPrestamo = prestamoService.obtenerPorId("1");
        assertTrue(actualizadoPrestamo.isPresent());
        assertEquals(EstadoPrestamo.PAGADO, actualizadoPrestamo.get().estado());
    }

    @Test
    void eliminarPrestamo_DeberiaEliminarElPrestamo() {
        Prestamo prestamo = new Prestamo("1", 2000.0, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        prestamoService.crearPrestamo(prestamo);

        boolean eliminado = prestamoService.eliminarPrestamo("1");

        assertTrue(eliminado, "El préstamo debería eliminarse.");
        assertTrue(prestamoService.obtenerPorId("1").isEmpty());
    }

    @Test
    void calcularMontoTotal_DeberiaAplicarTasaCorrecta_SegunTipoCliente() {
        Prestamo prestamo = new Prestamo("1", 1000, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);

        double totalVIP = prestamoService.calcularMontoTotal(prestamo, TipoCliente.VIP);
        double totalRegular = prestamoService.calcularMontoTotal(prestamo, TipoCliente.REGULAR);

        assertEquals(1050.0, totalVIP, 0.01, "El total para VIP debería ser 1050.0");
        assertEquals(1100.0, totalRegular, 0.01, "El total para REGULAR debería ser 1100.0");
    }
}
