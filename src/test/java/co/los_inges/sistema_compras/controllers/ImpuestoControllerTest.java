package co.los_inges.sistema_compras.controllers;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class ImpuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final Long ID_EXISTENTE = 3L;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/impuestos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(5)));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/impuestos/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idImpuesto").value(ID_EXISTENTE))
                .andExpect(jsonPath("$.nombre").isNotEmpty())
                .andExpect(jsonPath("$.porcentaje", is(0.0)))
                .andExpect(jsonPath("$.porcentaje").isNumber());
    }

    @Test
    void create() throws Exception {
        String nuevoImpuesto = """
            {
                "nombre": "IVA 15%",
                "porcentaje": 15.0
            }
        """;

        mockMvc.perform(post("/api/impuestos")
                        .content(nuevoImpuesto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("IVA 15%"))
                .andExpect(jsonPath("$.porcentaje").value(15.0));
    }

    @Test
    void update() throws Exception {
        String updateImpuesto = """
            {
                "nombre": "IVA 19%",
                "porcentaje": 19.0
            }
        """;

        mockMvc.perform(put("/api/impuestos/{id}", ID_EXISTENTE)
                        .content(updateImpuesto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("IVA 19%"))
                .andExpect(jsonPath("$.porcentaje").value(19.0));
    }

    @Test
    void deleteImpuesto() throws Exception {
        mockMvc.perform(delete("/api/impuestos/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/impuestos/{id}", ID_EXISTENTE))
                .andExpect(status().isNotFound());
    }
}