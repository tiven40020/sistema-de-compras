package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.ProveedorTelefonoService;
import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;
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
@RequestMapping("/api/proveedores-telefonos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(
        name = "Proveedores - Teléfonos",
        description = "Endpoints para gestionar los teléfonos asociados a proveedores del sistema."
)
public class ProveedorTelefonoController {

    private final ProveedorTelefonoService proveedorTelefonoService;

    // GET: listar todas las relaciones proveedor-teléfono
    @Operation(
            summary = "Obtener todas las relaciones proveedor-teléfono",
            description = "Devuelve una lista de todos los teléfonos registrados junto con su proveedor asociado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de relaciones proveedor-teléfono obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorTelefonoResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProveedorTelefonoResponseDTO>> getAll() {
        List<ProveedorTelefonoResponseDTO> lista = proveedorTelefonoService.getAllProveedorTelefonos();
        return ResponseEntity.ok(lista);
    }

    // GET: obtener una relación por ID
    @Operation(
            summary = "Obtener un teléfono de proveedor por ID",
            description = "Devuelve la información de un teléfono asociado a un proveedor según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Teléfono encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorTelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorTelefonoResponseDTO> getById(
            @Parameter(description = "ID de la relación proveedor-teléfono que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return proveedorTelefonoService.getProveedorTelefonoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear una nueva relación proveedor-teléfono
    @Operation(
            summary = "Registrar un nuevo teléfono de proveedor",
            description = """
                    Permite asociar un nuevo número de teléfono a un proveedor existente.
                    Debe incluir el ID del proveedor y los datos del teléfono (tipo, número, extensión, etc.).
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Teléfono de proveedor creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorTelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<ProveedorTelefonoResponseDTO> create(
            @Valid @RequestBody ProveedorTelefonoRequestDTO dto
    ) {
        ProveedorTelefonoResponseDTO nuevo = proveedorTelefonoService.createProveedorTelefono(dto);
        return ResponseEntity.created(URI.create("/api/proveedores-telefonos/" + nuevo.idProveedoresTelefonos()))
                .body(nuevo);
    }

    // PUT: actualizar un teléfono de proveedor existente
    @Operation(
            summary = "Actualizar un teléfono de proveedor existente",
            description = "Permite modificar los datos de un teléfono vinculado a un proveedor, como el número o tipo.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Teléfono de proveedor actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorTelefonoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorTelefonoResponseDTO> update(
            @Parameter(description = "ID de la relación proveedor-teléfono que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody ProveedorTelefonoRequestDTO dto
    ) {
        return proveedorTelefonoService.updateProveedorTelefono(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una relación proveedor-teléfono
    @Operation(
            summary = "Eliminar un teléfono de proveedor",
            description = "Elimina una relación entre un proveedor y un teléfono del sistema.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Teléfono eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Teléfono no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID de la relación proveedor-teléfono que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = proveedorTelefonoService.deleteProveedorTelefono(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
