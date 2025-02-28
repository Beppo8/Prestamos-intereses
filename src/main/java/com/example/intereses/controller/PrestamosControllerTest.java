package com.example.intereses.controller;

import com.example.intereses.model.EstadoPrestamo;
import com.example.intereses.model.Prestamo;
import com.example.intereses.service.PrestamoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PrestamoService prestamoService;

    @InjectMocks
    private PrestamoController prestamoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDePrestamos() throws Exception {
        List<Prestamo> prestamos = List.of(new Prestamo("1", 1000.0, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE));

        when(prestamoService.obtenerTodos()).thenReturn(prestamos);

        mockMvc.perform(get("/prestamos"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(prestamos)));
    }

    @Test
    void crearPrestamo_DeberiaAgregarUnPrestamo() throws Exception {
        Prestamo prestamo = new Prestamo("1", 1500.0, "C1", LocalDate.now(), EstadoPrestamo.PENDIENTE);

        mockMvc.perform(post("/prestamos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isOk());

        verify(prestamoService, times(1)).crearPrestamo(prestamo);
    }

    @Test
    void actualizarEstado_DeberiaCambiarEstadoDelPrestamo() throws Exception {
        when(prestamoService.actualizarEstado("1", EstadoPrestamo.PAGADO)).thenReturn(true);

        mockMvc.perform(put("/prestamos/1/estado?estado=PAGADO"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
