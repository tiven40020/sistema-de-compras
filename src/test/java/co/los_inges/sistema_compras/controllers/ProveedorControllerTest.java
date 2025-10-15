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
class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Long ID_EXISTENTE = 1L;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/proveedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/proveedores/{id}", ID_EXISTENTE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProveedor").value(ID_EXISTENTE));
    }

    @Test
    void create() throws Exception {
        String nuevoProveedor = """
            {
                "nombre": "Proveedor Test",
                "idCiudad": 1,
                "direccion": "Calle 123 #45-67",
                "email": "proveedor@test.com",
                "estado": true,
                "telefonos": ["+5712345678", "3001234567"]
            }
        """;

        mockMvc.perform(post("/api/proveedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoProveedor))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Proveedor Test"))
                .andExpect(jsonPath("$.email").value("proveedor@test.com"))
                .andExpect(jsonPath("$.telefonos").isArray())
                .andExpect(jsonPath("$.telefonos[0]").value("+5712345678"));
    }

    @Test
    void update() throws Exception {
        String updateProveedor = """
            {
                "nombre": "Proveedor Actualizado",
                "idCiudad": 1,
                "direccion": "Avenida 456 #78-90",
                "email": "actualizado@test.com",
                "estado": false,
                "telefonos": ["+5712345678"]
            }
        """;

        mockMvc.perform(put("/api/proveedores/{id}", ID_EXISTENTE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateProveedor))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Proveedor Actualizado"))
                .andExpect(jsonPath("$.estado").value(false));
    }

    @Test
    void deleteProveedor() throws Exception {
        mockMvc.perform(delete("/api/proveedores/{id}", ID_EXISTENTE))
                .andExpect(status().isNoContent());
    }
}