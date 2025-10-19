package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.MarcaService;
import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
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
@RequestMapping("/api/marcas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Marcas", description = "Endpoints para la gestión de marcas de productos.")
public class MarcaController {

    private final MarcaService marcaService;

    // GET: listar todas las marcas
    @Operation(
            summary = "Obtener todas las marcas",
            description = "Devuelve la lista completa de marcas registradas en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de marcas obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MarcaResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> getAll() {
        List<MarcaResponseDTO> marcas = marcaService.getAllMarcas();
        return ResponseEntity.ok(marcas);
    }

    // GET: obtener una marca por ID
    @Operation(
            summary = "Obtener una marca por ID",
            description = "Devuelve los datos de una marca específica según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Marca encontrada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MarcaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Marca no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> getById(
            @Parameter(description = "ID de la marca que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return marcaService.getMarcaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear una nueva marca
    @Operation(
            summary = "Registrar una nueva marca",
            description = "Permite crear una nueva marca en el sistema, indicando su nombre y descripción opcional.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Marca creada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MarcaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<MarcaResponseDTO> create(
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        MarcaResponseDTO nueva = marcaService.createMarca(dto);
        return ResponseEntity.created(URI.create("/api/marcas/" + nueva.idMarca()))
                .body(nueva);
    }

    // PUT: actualizar una marca existente
    @Operation(
            summary = "Actualizar una marca existente",
            description = "Permite modificar el nombre o la descripción de una marca previamente registrada.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Marca actualizada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MarcaResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Marca no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> update(
            @Parameter(description = "ID de la marca que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        return marcaService.updateMarca(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una marca
    @Operation(
            summary = "Eliminar una marca",
            description = "Elimina una marca del sistema según su identificador.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Marca eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Marca no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la marca que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = marcaService.deleteMarca(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
