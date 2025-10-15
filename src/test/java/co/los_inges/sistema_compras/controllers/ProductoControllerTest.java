package co.los_inges.sistema_compras.controllers;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Long ID_EXISTENTE = 1L;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/productos/{id}", ID_EXISTENTE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(ID_EXISTENTE));
    }

    @Test
    void create() throws Exception {
        String nuevoProducto = """
            {
                "nombre": "Producto Test",
                "idCategoria": 1,
                "idMarca": 1,
                "idUnidadMedida": 1,
                "cantidadUnidadesMedidas": 10,
                "idImpuesto": 1,
                "precio": 100000,
                "stock": 50,
                "estado": true
            }
        """;

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoProducto))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Producto Test"))
                .andExpect(jsonPath("$.precio").value(100000))
                .andExpect(jsonPath("$.stock").value(50));
    }

    @Test
    void update() throws Exception {
        String updateProducto = """
            {
                "nombre": "Producto Actualizado",
                "idCategoria": 1,
                "idMarca": 1,
                "idUnidadMedida": 1,
                "cantidadUnidadesMedidas": 20,
                "idImpuesto": 1,
                "precio": 120000,
                "stock": 30,
                "estado": true
            }
        """;

        mockMvc.perform(put("/api/productos/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateProducto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto Actualizado"))
                .andExpect(jsonPath("$.precio").value(120000))
                .andExpect(jsonPath("$.stock").value(30));
    }

    @Test
    void deleteProducto() throws Exception {

        mockMvc.perform(delete("/api/productos/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());
    }
}