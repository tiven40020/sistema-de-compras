package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")

class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    void getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsuarios();

        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty(), "La lista de usuarios esta vacia");
        assertTrue(usuarios.size() >= 5);
    }

    @Test
    void getUsuarioById() {
        Optional<UsuarioResponseDTO> usuario = usuarioService.getUsuarioById(1L);

        assertTrue(usuario.isPresent(), "Debe existir un usuario con ID 1 en el dataset");
        assertNotNull(usuario.get().nombre());
        assertNotNull(usuario.get().email());
    }

    @Test
    void createUsuario() {
        UsuarioRequestDTO request = new UsuarioRequestDTO(
                "Sara Lopez",
                "sara.lopez@empresa.com",
                "3007896541",
                "sara12345"
        );

        UsuarioResponseDTO creado = usuarioService.createUsuario(request);

        assertNotNull(creado, "El usuario creado no debe ser nulo");
        assertNotNull(creado.idUsuario(), "Debe generarse un ID para el nuevo usuario");
        assertEquals("Sara Lopez", creado.nombre());
        assertEquals("sara.lopez@empresa.com", creado.email());
    }

    @Test
    void updateUsuario() {
        Optional<UsuarioResponseDTO> existente = usuarioService.getUsuarioById(2L);
        assertTrue(existente.isPresent(), "Debe existir un usuario con ID 2 en el dataset para actualizarlo");

        UsuarioRequestDTO request = new UsuarioRequestDTO(
                "Carlos Perez",
                "carlos.perez@empresa.com",
                "3214569870",
                "nuevoPass456"
        );

        Optional<UsuarioResponseDTO> actualizado = usuarioService.updateUsuario(2L, request);

        assertTrue(actualizado.isPresent(), "El usuario con ID 2 no se pudo actualizar");
        assertEquals("Carlos Perez", actualizado.get().nombre());
        assertEquals("carlos.perez@empresa.com", actualizado.get().email());
    }

    @Test
    void deleteUsuario() {
        Optional<UsuarioResponseDTO> usuario = usuarioService.getUsuarioById(3L);
        assertTrue(usuario.isPresent(), "Debe existir un usuario con ID 3 en el dataset");

        boolean eliminado = usuarioService.deleteUsuario(3L);

        assertTrue(eliminado, "No se elimino correctamente el usuario con ID 3");
        assertTrue(usuarioService.getUsuarioById(3L).isEmpty(), "Después de eliminar, el usuario no debe existir");
    }
}