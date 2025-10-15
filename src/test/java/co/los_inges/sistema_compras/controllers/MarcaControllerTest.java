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
class MarcaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/marcas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getById() throws Exception {
        Long ID_EXISTENTE = 1L;

        mockMvc.perform(get("/api/marcas/{id}", ID_EXISTENTE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMarca").value(ID_EXISTENTE));
    }

    @Test
    void create() throws Exception {
        String nuevaMarca = """
                {
                    "nombre": "Marca Test"
                }
                """;

        mockMvc.perform(post("/api/marcas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevaMarca))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Marca Test"));
    }

    @Test
    void update() throws Exception {
        Long ID_EXISTENTE = 1L;

        String updateMarca = """
                {
                    "nombre": "Marca Actualizada"
                }
                """;

        mockMvc.perform(put("/api/marcas/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateMarca))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Marca Actualizada"));
    }

    @Test
    void deleteMarca() throws Exception {
        Long ID_MARCA = 2L;

        mockMvc.perform(delete("/api/marcas/{id}", ID_MARCA))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/marcas/{id}", ID_MARCA))
                .andExpect(status().isNotFound());
    }
}