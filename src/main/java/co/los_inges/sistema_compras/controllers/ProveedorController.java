package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.ProveedorService;
import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
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
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Proveedores", description = "Endpoints para la gestión de proveedores, incluyendo ciudad, contacto y teléfonos asociados.")
public class ProveedorController {

    private final ProveedorService proveedorService;

    // GET: listar todos los proveedores
    @Operation(
            summary = "Obtener todos los proveedores",
            description = "Devuelve la lista completa de proveedores registrados, incluyendo su ciudad, correo electrónico, NIT y teléfonos asociados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de proveedores obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> getAll() {
        List<ProveedorResponseDTO> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    // GET: obtener un proveedor por ID
    @Operation(
            summary = "Obtener un proveedor por ID",
            description = "Devuelve la información completa de un proveedor, incluyendo su ciudad y teléfonos registrados.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proveedor encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> getById(
            @Parameter(description = "ID del proveedor que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return proveedorService.getProveedorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: registrar un nuevo proveedor
    @Operation(
            summary = "Registrar un nuevo proveedor",
            description = """
                    Permite registrar un nuevo proveedor en el sistema.
                    Debe incluir su nombre, NIT, correo electrónico, dirección, la ciudad a la que pertenece
                    y una lista de teléfonos de contacto.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Proveedor creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> create(
            @Valid @RequestBody ProveedorRequestDTO dto
    ) {
        ProveedorResponseDTO nuevo = proveedorService.createProveedor(dto);
        return ResponseEntity.created(URI.create("/api/proveedores/" + nuevo.idProveedor()))
                .body(nuevo);
    }

    // PUT: actualizar un proveedor existente
    @Operation(
            summary = "Actualizar un proveedor existente",
            description = "Permite modificar la información de un proveedor registrado (nombre, NIT, ciudad, teléfonos, etc.).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Proveedor actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProveedorResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> update(
            @Parameter(description = "ID del proveedor que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody ProveedorRequestDTO dto
    ) {
        return proveedorService.updateProveedor(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un proveedor
    @Operation(
            summary = "Eliminar un proveedor",
            description = "Elimina un proveedor del sistema. Si el proveedor tiene compras asociadas, se recomienda validar antes de eliminar.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Proveedor eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del proveedor que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = proveedorService.deleteProveedor(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
