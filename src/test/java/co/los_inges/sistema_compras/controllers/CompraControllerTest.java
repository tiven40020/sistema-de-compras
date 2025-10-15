package co.los_inges.sistema_compras.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Long ID_EXISTENTE = 2L;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/compras")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Verificamos que contenga compras con facturas conocidas
                .andExpect(jsonPath("$[*].numeroFactura", hasItems(1001,1002)))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/compras/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCompra").value(ID_EXISTENTE))
                .andExpect(jsonPath("$.numeroFactura").isNotEmpty())
                .andExpect(jsonPath("$.fecha").isNotEmpty());
    }

    @Test
    void create() throws Exception {

        mockMvc.perform(post("/api/compras")
                        .content("""
                                {
                                    "numeroFactura": 2001,
                                    "fecha": "2025-10-10",
                                    "idProveedor": 1,
                                    "idUsuario": 3
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCompra", notNullValue()))
                .andExpect(jsonPath("$.numeroFactura").value(2001))
                .andExpect(jsonPath("$.proveedor.idProveedor").value(1));

    }

    @Test
    void update() throws Exception {

        mockMvc.perform(put("/api/compras/{id}", ID_EXISTENTE)
                        .content("""
                                {
                                    "numeroFactura": 9999,
                                    "fecha": "2025-12-31",
                                    "idProveedor": 2,
                                    "idUsuario": 2
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroFactura").value(9999))
                .andExpect(jsonPath("$.fecha").value("2025-12-30"))
                .andExpect(jsonPath("$.proveedor.idProveedor").value(2));
    }

    @Test
    void deleteCompra() throws Exception {
        mockMvc.perform(delete("/api/compras/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/compras/{id}", ID_EXISTENTE))
                .andExpect(status().isNotFound());
    }
}