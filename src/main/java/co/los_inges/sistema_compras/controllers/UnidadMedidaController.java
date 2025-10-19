package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.UnidadMedidaService;
import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;
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
@RequestMapping("/api/unidades-medida")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Unidades de Medida", description = "Endpoints para la gestión de unidades de medida (por ejemplo: kilogramo, litro, unidad, caja).")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    // GET: listar todas las unidades de medida
    @Operation(
            summary = "Obtener todas las unidades de medida",
            description = "Devuelve una lista completa con todas las unidades de medida registradas en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de unidades de medida obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UnidadMedidaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "204", description = "No hay unidades de medida registradas")
            }
    )
    @GetMapping
    public ResponseEntity<List<UnidadMedidaResponseDTO>> getAll() {
        List<UnidadMedidaResponseDTO> unidades = unidadMedidaService.getAllUnidadesMedida();
        return unidades.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(unidades);
    }

    // GET: obtener una unidad por ID
    @Operation(
            summary = "Obtener una unidad de medida por ID",
            description = "Devuelve los datos de una unidad de medida específica identificada por su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Unidad de medida encontrada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UnidadMedidaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> getById(
            @Parameter(description = "ID de la unidad de medida que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return unidadMedidaService.getUnidadMedidaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear una nueva unidad de medida
    @Operation(
            summary = "Registrar una nueva unidad de medida",
            description = """
                    Permite crear una nueva unidad de medida en el sistema.
                    Debe incluir el nombre de la unidad (por ejemplo, "Kilogramo") y su abreviatura (por ejemplo, "kg").
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Unidad de medida creada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UnidadMedidaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<UnidadMedidaResponseDTO> create(
            @Valid @RequestBody UnidadMedidaRequestDTO dto
    ) {
        UnidadMedidaResponseDTO nueva = unidadMedidaService.createUnidadMedida(dto);
        return ResponseEntity.created(URI.create("/api/unidades-medida/" + nueva.idUnidadMedida()))
                .body(nueva);
    }

    // PUT: actualizar una unidad existente
    @Operation(
            summary = "Actualizar una unidad de medida existente",
            description = "Permite modificar los datos de una unidad de medida registrada, como el nombre o la abreviatura.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Unidad de medida actualizada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UnidadMedidaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> update(
            @Parameter(description = "ID de la unidad de medida que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody UnidadMedidaRequestDTO dto
    ) {
        return unidadMedidaService.updateUnidadMedida(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una unidad de medida
    @Operation(
            summary = "Eliminar una unidad de medida",
            description = "Elimina una unidad de medida del sistema según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Unidad de medida eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Unidad de medida no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la unidad de medida que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = unidadMedidaService.deleteUnidadMedida(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
