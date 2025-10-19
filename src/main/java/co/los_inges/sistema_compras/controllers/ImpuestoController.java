package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.ImpuestoService;
import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;
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
@RequestMapping("/api/impuestos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Impuestos", description = "Endpoints para la gestión de impuestos aplicables a los productos y compras.")
public class ImpuestoController {

    private final ImpuestoService impuestoService;


    // GET: listar todos los impuestos
    @Operation(
            summary = "Obtener todos los impuestos",
            description = "Devuelve la lista completa de impuestos registrados en el sistema, con su nombre, porcentaje y descripción.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de impuestos obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ImpuestoResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ImpuestoResponseDTO>> getAll() {
        List<ImpuestoResponseDTO> impuestos = impuestoService.getAllImpuestos();
        return ResponseEntity.ok(impuestos);
    }

    // GET: obtener un impuesto por ID
    @Operation(
            summary = "Obtener un impuesto por ID",
            description = "Devuelve los datos de un impuesto específico según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Impuesto encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ImpuestoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Impuesto no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoResponseDTO> getById(@PathVariable long id) {
        return impuestoService.getImpuestoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: registrar un nuevo impuesto
    @Operation(
            summary = "Registrar un nuevo impuesto",
            description = "Permite crear un nuevo impuesto, especificando su nombre, descripción y porcentaje de aplicación.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Impuesto creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ImpuestoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<ImpuestoResponseDTO> create(@Valid @RequestBody ImpuestoRequestDTO dto) {
        ImpuestoResponseDTO nuevo = impuestoService.createImpuesto(dto);
        return ResponseEntity.created(URI.create("/api/impuestos/" + nuevo.idImpuesto()))
                .body(nuevo);
    }

    // PUT: actualizar un impuesto existente
    @Operation(
            summary = "Actualizar un impuesto existente",
            description = "Permite modificar los datos de un impuesto previamente registrado (nombre, descripción o porcentaje).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Impuesto actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ImpuestoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Impuesto no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody ImpuestoRequestDTO dto
    ) {
        return impuestoService.updateImpuesto(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un impuesto
    @Operation(
            summary = "Eliminar un impuesto",
            description = "Elimina un impuesto del sistema según su identificador.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Impuesto eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Impuesto no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = impuestoService.deleteImpuesto(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
