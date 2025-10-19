package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.DetalleCompraService;
import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;
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
@RequestMapping("/api/detalles-compra")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Detalles de Compra", description = "Endpoints para la gestión de los productos incluidos en cada compra.")
public class DetalleCompraController {

    private final DetalleCompraService detalleCompraService;


    // GET: listar todos los detalles
    @Operation(
            summary = "Obtener todos los detalles de compra",
            description = "Devuelve una lista con todos los detalles registrados. Cada detalle pertenece a una compra e incluye producto, cantidad y subtotal.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de detalles obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DetalleCompraResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<DetalleCompraResponseDTO>> getAll() {
        List<DetalleCompraResponseDTO> detalles = detalleCompraService.getAllDetalles();
        return ResponseEntity.ok(detalles);
    }

    // GET: obtener un detalle por ID
    @Operation(
            summary = "Obtener un detalle de compra por ID",
            description = "Devuelve la información de un detalle específico, incluyendo el producto y la compra asociada.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalle encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DetalleCompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> getById(@PathVariable long id) {
        return detalleCompraService.getDetalleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: registrar un nuevo detalle
    @Operation(
            summary = "Registrar un nuevo detalle de compra",
            description = "Crea un nuevo detalle de compra con los datos del producto, cantidad, precio unitario y referencia a la compra.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Detalle de compra creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DetalleCompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<DetalleCompraResponseDTO> create(@Valid @RequestBody DetalleCompraRequestDTO dto) {
        DetalleCompraResponseDTO nuevo = detalleCompraService.createDetalle(dto);
        return ResponseEntity.created(URI.create("/api/detalles-compra/" + nuevo.idDetalleCompra()))
                .body(nuevo);
    }

    // 🔹 PUT: actualizar un detalle existente
    @Operation(
            summary = "Actualizar un detalle de compra existente",
            description = "Permite modificar los datos de un detalle de compra, como la cantidad, precio o el producto asociado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Detalle de compra actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DetalleCompraResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody DetalleCompraRequestDTO dto
    ) {
        return detalleCompraService.updateDetalle(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un detalle de compra
    @Operation(
            summary = "Eliminar un detalle de compra",
            description = "Elimina un detalle de compra del sistema. Esto no elimina la compra completa, solo el detalle seleccionado.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Detalle eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = detalleCompraService.deleteDetalle(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
