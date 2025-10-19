package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.TelefonoService;
import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/telefonos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Teléfonos", description = "Endpoints para la gestión de teléfonos en el sistema (asociados a proveedores, usuarios u otras entidades).")
public class TelefonoController {

    private final TelefonoService telefonoService;

    // GET: listar todos los teléfonos
    @Operation(
            summary = "Obtener todos los teléfonos",
            description = "Devuelve una lista completa de teléfonos registrados en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de teléfonos obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "204", description = "No hay teléfonos registrados")
            }
    )
    @GetMapping
    public ResponseEntity<List<TelefonoResponseDTO>> getAll() {
        List<TelefonoResponseDTO> telefonos = telefonoService.getAllTelefonos();
        return telefonos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(telefonos);
    }

    // GET: obtener un teléfono por ID
    @Operation(
            summary = "Obtener un teléfono por ID",
            description = "Devuelve los datos de un teléfono específico identificado por su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Teléfono encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TelefonoResponseDTO> getById(
            @Parameter(description = "ID del teléfono que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return telefonoService.getTelefonoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear un nuevo teléfono
    @Operation(
            summary = "Registrar un nuevo teléfono",
            description = """
                    Permite crear un nuevo teléfono en el sistema.
                    El campo 'numero' es obligatorio y puede incluir un tipo (celular, oficina, fijo, etc.).
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Teléfono creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<TelefonoResponseDTO> create(
            @Valid @RequestBody TelefonoRequestDTO dto
    ) {
        TelefonoResponseDTO nuevo = telefonoService.createTelefono(dto);
        return ResponseEntity.created(URI.create("/api/telefonos/" + nuevo.idTelefono()))
                .body(nuevo);
    }

    // PUT: actualizar un teléfono existente
    @Operation(
            summary = "Actualizar un teléfono existente",
            description = "Permite modificar el número o tipo de un teléfono registrado en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Teléfono actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<TelefonoResponseDTO> update(
            @Parameter(description = "ID del teléfono que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody TelefonoRequestDTO dto
    ) {
        return telefonoService.updateTelefono(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un teléfono
    @Operation(
            summary = "Eliminar un teléfono",
            description = "Elimina un teléfono del sistema según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Teléfono eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del teléfono que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = telefonoService.deleteTelefono(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
