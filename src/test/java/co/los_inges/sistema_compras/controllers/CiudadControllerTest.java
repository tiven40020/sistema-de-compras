package co.los_inges.sistema_compras.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CiudadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Long ID_EXISTENTE = 1L;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/ciudades")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                // Verificamos que contenga nombres específicos de data.sql
                .andExpect(jsonPath("$[*].nombre", hasItems("Medellin", "Bogota", "Cali")))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(5))));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/ciudades/{id}", ID_EXISTENTE))
                .andExpect(status().isOk())
                // Verificamos que el nombre coincida con data.sql
                .andExpect(jsonPath("$.idCiudad").value(ID_EXISTENTE))
                .andExpect(jsonPath("$.nombre", is("Medellin")));
    }

    @Test
    void create() throws Exception {
        // Insertamos una ciudad nueva que no esté en data.sql
        mockMvc.perform(post("/api/ciudades")
                        .content("{\"nombre\": \"Pereira\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                // Validamos que tenga id generado y nombre correcto
                .andExpect(jsonPath("$.idCiudad", notNullValue()))
                .andExpect(jsonPath("$.nombre", equalTo("Pereira")));
    }

    @Test
    void update() throws Exception {

        mockMvc.perform(put("/api/ciudades/{id}", ID_EXISTENTE)
                        .content("{\"nombre\": \"Medellin Actualizada\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", containsString("Actualizada")));
    }

    @Test
    void deleteCiudad() throws Exception {
        mockMvc.perform(delete("/api/ciudades/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/ciudades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].idCiudad", not(hasItem(ID_EXISTENTE.intValue()))));
    }
}