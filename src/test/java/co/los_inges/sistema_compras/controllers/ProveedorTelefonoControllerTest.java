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
class ProveedorTelefonoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/proveedores-telefonos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/proveedores-telefonos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProveedoresTelefonos").value(1));
    }

    @Test
    void create() throws Exception {
        String nuevoProveedorTelefono = """
        {
            "idProveedor": 1,
            "idTelefono": 1
        }
        """;

        mockMvc.perform(post("/api/proveedores-telefonos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoProveedorTelefono))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.proveedor.idProveedor").value(1))
                .andExpect(jsonPath("$.telefono.idTelefono").value(1));
    }

    @Test
    void update() throws Exception {
        String actualizar = """
        {
            "idProveedor": 2,
            "idTelefono": 2
        }
        """;

        mockMvc.perform(put("/api/proveedores-telefonos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualizar))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.proveedor.idProveedor").value(2))
                .andExpect(jsonPath("$.telefono.idTelefono").value(2));
    }

    @Test
    void deleteProveedorTelefono() throws Exception {
        mockMvc.perform(delete("/api/proveedores-telefonos/{id}", 1))
                .andExpect(status().isNoContent());
    }
}