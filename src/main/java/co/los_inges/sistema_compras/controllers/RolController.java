package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.dtos.request.RolRequestDTO;
import co.los_inges.sistema_compras.dtos.response.RolResponseDTO;
import co.los_inges.sistema_compras.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Roles", description = "Endpoints para la gestión de roles del sistema (ADMIN, USER, etc.).")
public class RolController {

    private final RolService rolService;

    // GET: listar todos los roles
    @Operation(
            summary = "Obtener todos los roles",
            description = "Devuelve una lista con todos los roles registrados en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de roles obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "204", description = "No existen roles registrados")
            }
    )
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> getAllRoles() {
        List<RolResponseDTO> roles = rolService.getAllRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    // GET: obtener un rol por ID
    @Operation(
            summary = "Obtener un rol por ID",
            description = "Devuelve los datos de un rol específico identificado por su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rol encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Rol no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> getRolById(
            @Parameter(description = "ID del rol que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return rolService.getRolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear un nuevo rol
    @Operation(
            summary = "Registrar un nuevo rol",
            description = """
                    Permite crear un nuevo rol en el sistema. 
                    Se debe especificar el nombre del rol (por ejemplo, ADMIN, USER) y una descripción opcional.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Rol creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<RolResponseDTO> createRol(
            @Valid @RequestBody RolRequestDTO rolRequestDTO
    ) {
        RolResponseDTO nuevoRol = rolService.createRol(rolRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    // PUT: actualizar un rol existente
    @Operation(
            summary = "Actualizar un rol existente",
            description = "Permite modificar el nombre o la descripción de un rol ya registrado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rol actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RolResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Rol no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateRol(
            @Parameter(description = "ID del rol que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody RolRequestDTO rolRequestDTO
    ) {
        return rolService.updateRol(id, rolRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un rol
    @Operation(
            summary = "Eliminar un rol",
            description = """
                    Elimina un rol del sistema. 
                    Se recomienda no eliminar roles que estén asociados a usuarios activos.
                    """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Rol no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(
            @Parameter(description = "ID del rol que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean eliminado = rolService.deleteRol(id);
        return eliminado
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
