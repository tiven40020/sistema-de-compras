package co.los_inges.sistema_compras.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DetalleCompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Long ID_EXISTENTE = 2L;


    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/detalles-compra")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(5)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/detalles-compra/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDetalleCompra").value(ID_EXISTENTE))
                .andExpect(jsonPath("$.cantidad").isNumber())
                .andExpect(jsonPath("$.subtotal").isNumber());
    }

    @Test
    void create() throws Exception {
        String nuevoDetalle = """
            {
                "idCompra": 1,
                "idProducto": 2,
                "cantidad": 3
            }
        """;

        mockMvc.perform(post("/api/detalles-compra")
                        .content(nuevoDetalle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cantidad").value(3));
    }

    @Test
    void update() throws Exception {
        String updateDetalle = """
                    {
                        "idCompra": 1,
                        "idProducto": 2,
                        "cantidad": 10
                    }
                """;

        mockMvc.perform(put("/api/detalles-compra/{id}", ID_EXISTENTE)
                        .content(updateDetalle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(10));
    }

    @Test
    void deleteDetalleCompra() throws Exception {
        mockMvc.perform(delete("/api/detalles-compra/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/detalles-compra/{id}", ID_EXISTENTE))
                .andExpect(status().isNotFound());
    }
}