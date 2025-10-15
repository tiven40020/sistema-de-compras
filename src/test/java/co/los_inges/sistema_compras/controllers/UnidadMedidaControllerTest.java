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

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UnidadMedidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/unidades-medida"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/unidades-medida/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUnidadMedida").value(1))
                .andExpect(jsonPath("$.nombre").exists());
    }

    @Test
    void create() throws Exception {
        String nuevaUnidad = """
        {
            "nombre": "Gramos"
        }
        """;

        mockMvc.perform(post("/api/unidades-medida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevaUnidad))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Gramos"));
    }

    @Test
    void update() throws Exception {
        String actualizarUnidad = """
        {
            "nombre": "Longitud"
        }
        """;

        mockMvc.perform(put("/api/unidades-medida/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualizarUnidad))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUnidadMedida").value(1))
                .andExpect(jsonPath("$.nombre").value("Longitud"));
    }

    @Test
    void deleteUnidadMedida() throws Exception {
        mockMvc.perform(delete("/api/unidades-medida/{id}", 1))
                .andExpect(status().isNoContent());
    }
}