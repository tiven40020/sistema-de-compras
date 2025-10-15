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
class TelefonoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/telefonos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/telefonos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTelefono").value(1))
                .andExpect(jsonPath("$.numero").exists());
    }

    @Test
    void create() throws Exception {
        String nuevoTelefono = """
        {
            "numero": "+573205551234"
        }
        """;

        mockMvc.perform(post("/api/telefonos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoTelefono))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numero").value("+573205551234"));
    }

    @Test
    void update() throws Exception {
        String actualizarTelefono = """
        {
            "numero": "+573205559999"
        }
        """;

        mockMvc.perform(put("/api/telefonos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualizarTelefono))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTelefono").value(1))
                .andExpect(jsonPath("$.numero").value("+573205559999"));
    }

    @Test
    void deleteTelefono() throws Exception {
        mockMvc.perform(delete("/api/telefonos/{id}", 1))
                .andExpect(status().isNoContent());
    }
}