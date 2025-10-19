package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.CompraService;
import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Compras", description = "Endpoints para la gestión de compras realizadas a proveedores.")
public class CompraController {

    private final CompraService compraService;


    // GET: listar todas las compras
    @Operation(
            summary = "Obtener todas las compras",
            description = "Devuelve una lista con todas las compras registradas, incluyendo proveedor, usuario y detalles.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de compras obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CompraResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> getAll() {
        List<CompraResponseDTO> compras = compraService.getAllCompras();
        return ResponseEntity.ok(compras);
    }

    // GET: obtener una compra por ID
    @Operation(
            summary = "Obtener una compra por ID",
            description = "Devuelve la información detallada de una compra específica según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Compra encontrada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Compra no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> getById(@PathVariable long id) {
        return compraService.getCompraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: registrar una nueva compra
    @Operation(
            summary = "Registrar una nueva compra",
            description = "Registra una nueva compra con los datos del proveedor, usuario y los productos asociados.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Compra creada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<CompraResponseDTO> create(@Valid @RequestBody CompraRequestDTO dto) {
        CompraResponseDTO nuevaCompra = compraService.createCompra(dto);
        return ResponseEntity.created(URI.create("/api/compras/" + nuevaCompra.idCompra()))
                .body(nuevaCompra);
    }

    // PUT: actualizar una compra existente
    @Operation(
            summary = "Actualizar una compra existente",
            description = "Permite modificar los datos de una compra previamente registrada, incluyendo proveedor o productos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Compra actualizada correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Compra no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody CompraRequestDTO dto
    ) {
        return compraService.updateCompra(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una compra
    @Operation(
            summary = "Eliminar una compra",
            description = "Elimina una compra del sistema por su identificador. Esta acción también puede afectar los detalles asociados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Compra eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Compra no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = compraService.deleteCompra(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
