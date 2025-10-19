package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.CiudadService;
import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Ciudades", description = "Endpoints para la gestión de ciudades y su información asociada.")
public class CiudadController {

    private final CiudadService ciudadService;

    // GET: listar todas las ciudades
    @Operation(
            summary = "Obtener todas las ciudades",
            description = "Devuelve la lista completa de ciudades registradas en el sistema.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de ciudades obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CiudadResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CiudadResponseDTO>> getAll() {
        List<CiudadResponseDTO> ciudades = ciudadService.getAllCiudades();
        return ResponseEntity.ok(ciudades);
    }

    // GET: obtener una ciudad por ID
    @Operation(
            summary = "Obtener una ciudad por ID",
            description = "Devuelve la información de una ciudad específica según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ciudad encontrada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CiudadResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CiudadResponseDTO> getById(@PathVariable long id) {
        return ciudadService.getCiudadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear una nueva ciudad
    @Operation(
            summary = "Crear una nueva ciudad",
            description = "Crea una nueva ciudad con los datos proporcionados en el cuerpo de la solicitud.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Ciudad creada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CiudadResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
            }
    )
    @PostMapping
    public ResponseEntity<CiudadResponseDTO> create(@Valid @RequestBody CiudadRequestDTO dto) {
        CiudadResponseDTO nueva = ciudadService.createCiudad(dto);
        return ResponseEntity.created(URI.create("/api/ciudades/" + nueva.idCiudad()))
                .body(nueva);
    }

    // PUT: actualizar una ciudad existente
    @Operation(
            summary = "Actualizar una ciudad existente",
            description = "Actualiza los datos de una ciudad existente según su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ciudad actualizada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CiudadResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CiudadResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody CiudadRequestDTO dto
    ) {
        return ciudadService.updateCiudad(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una ciudad
    @Operation(
            summary = "Eliminar una ciudad",
            description = "Elimina una ciudad del sistema según su identificador.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ciudad eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Ciudad no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = ciudadService.deleteCiudad(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
