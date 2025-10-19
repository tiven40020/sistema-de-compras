package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.UsuarioService;
import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios dentro del sistema. Requieren autenticación JWT.")
@SecurityRequirement(name = "bearerAuth") // 🔒 Aplica el esquema JWT a todos los métodos
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET: listar todos los usuarios
    @Operation(
            summary = "Obtener todos los usuarios",
            description = """
                    Devuelve una lista completa con todos los usuarios registrados en el sistema.
                    Este endpoint requiere un token JWT válido (solo accesible por roles con permiso de administración).
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de usuarios obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "No autorizado — Falta o es inválido el token JWT"),
                    @ApiResponse(responseCode = "403", description = "Prohibido — El usuario no tiene permisos suficientes")
            }
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsuarios();
        return usuarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(usuarios);
    }

    // GET: obtener un usuario por ID
    @Operation(
            summary = "Obtener un usuario por ID",
            description = "Devuelve los datos de un usuario específico identificado por su ID. Requiere autenticación JWT.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "401", description = "No autorizado — Token inválido o ausente")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(
            @Parameter(description = "ID del usuario que se desea consultar", example = "5")
            @PathVariable long id
    ) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear un nuevo usuario
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = """
                    Crea un nuevo usuario en el sistema.  
                    Este endpoint requiere los siguientes campos:
                    - nombre  
                    - email  
                    - telefono  
                    - password  
                    - nombreRol (nombre del rol que se asignará, por ejemplo: "ADMIN" o "USER")
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos"),
                    @ApiResponse(responseCode = "409", description = "Ya existe un usuario con ese correo")
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(
            @Valid @RequestBody UsuarioRequestDTO dto
    ) {
        UsuarioResponseDTO nuevo = usuarioService.createUsuario(dto);
        return ResponseEntity.created(URI.create("/api/usuarios/" + nuevo.idUsuario()))
                .body(nuevo);
    }

    // PUT: actualizar un usuario existente
    @Operation(
            summary = "Actualizar los datos de un usuario",
            description = """
                    Permite modificar los datos de un usuario existente, 
                    incluyendo su nombre, teléfono o rol asignado. 
                    Requiere autenticación JWT.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "401", description = "Token JWT inválido o ausente")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(
            @Parameter(description = "ID del usuario que se desea actualizar", example = "5")
            @PathVariable long id,
            @Valid @RequestBody UsuarioRequestDTO dto
    ) {
        return usuarioService.updateUsuario(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un usuario
    @Operation(
            summary = "Eliminar un usuario",
            description = """
                    Elimina un usuario del sistema.  
                    Solo los usuarios con rol ADMIN pueden ejecutar esta acción.  
                    Requiere autenticación JWT.
                    """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "401", description = "Token JWT inválido o ausente"),
                    @ApiResponse(responseCode = "403", description = "El usuario autenticado no tiene permisos suficientes")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del usuario que se desea eliminar", example = "5")
            @PathVariable long id
    ) {
        boolean deleted = usuarioService.deleteUsuario(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
