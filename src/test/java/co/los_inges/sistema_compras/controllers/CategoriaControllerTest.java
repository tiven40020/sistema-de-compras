package co.los_inges.sistema_compras.controllers;

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
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Long ID_EXISTENTE = 1L; // debe existir en data.sql

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThanOrEqualTo(5)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/categorias/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(ID_EXISTENTE))
                .andExpect(jsonPath("$.nombre").isNotEmpty());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/categorias")
                        .content("{\"nombre\": \"Nueva Categoria Test\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Nueva Categoria Test"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/api/categorias/{id}", ID_EXISTENTE)
                        .content("{\"nombre\": \"Categoria Actualizada\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Categoria Actualizada"));
    }

    @Test
    void deleteCategoria() throws Exception {
        mockMvc.perform(delete("/api/categorias/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/categorias/{id}", ID_EXISTENTE))
                .andExpect(status().isNotFound());
    }
}