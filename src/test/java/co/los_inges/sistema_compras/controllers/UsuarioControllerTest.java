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
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/api/usuarios/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombre").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.telefono").exists());
    }

    @Test
    void create() throws Exception {
        String nuevoUsuario = """
        {
            "nombre": "Usuario Test",
            "email": "usuario@test.com",
            "telefono": "+5712345678",
            "password": "password123"
        }
        """;

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nuevoUsuario))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Usuario Test"))
                .andExpect(jsonPath("$.email").value("usuario@test.com"))
                .andExpect(jsonPath("$.telefono").value("+5712345678"));
    }

    @Test
    void update() throws Exception {
        String actualizarUsuario = """
        {
            "nombre": "Usuario Actualizado",
            "email": "actualizado@test.com",
            "telefono": "+5711122233",
            "password": "nuevapassword"
        }
        """;

        mockMvc.perform(put("/api/usuarios/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualizarUsuario))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1))
                .andExpect(jsonPath("$.nombre").value("Usuario Actualizado"))
                .andExpect(jsonPath("$.email").value("actualizado@test.com"))
                .andExpect(jsonPath("$.telefono").value("+5711122233"));
    }

    @Test
    void deleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/{id}", 1))
                .andExpect(status().isNoContent());
    }
}