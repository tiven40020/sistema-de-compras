package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.ProductoService;
import co.los_inges.sistema_compras.dtos.request.ProductoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProductoResponseDTO;
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
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos, incluyendo categorías, marcas e impuestos.")
public class ProductoController {

    private final ProductoService productoService;

    // GET: listar todos los productos
    @Operation(
            summary = "Obtener todos los productos",
            description = "Devuelve la lista completa de productos registrados en el sistema, incluyendo su categoría, marca, unidad de medida e impuesto asociado.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de productos obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductoResponseDTO.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAll() {
        List<ProductoResponseDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    // GET: obtener un producto por ID
    @Operation(
            summary = "Obtener un producto por ID",
            description = "Devuelve los datos detallados de un producto según su identificador.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto encontrado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getById(
            @Parameter(description = "ID del producto que se desea consultar", example = "1")
            @PathVariable long id
    ) {
        return productoService.getProductoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: registrar un nuevo producto
    @Operation(
            summary = "Registrar un nuevo producto",
            description = "Permite crear un nuevo producto en el sistema. Se deben indicar sus atributos principales como nombre, precio, stock, categoría, marca, unidad de medida e impuesto asociado.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Producto creado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos o faltantes en la solicitud")
            }
    )
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> create(
            @Valid @RequestBody ProductoRequestDTO dto
    ) {
        ProductoResponseDTO nuevo = productoService.createProducto(dto);
        return ResponseEntity.created(URI.create("/api/productos/" + nuevo.idProducto()))
                .body(nuevo);
    }

    // PUT: actualizar un producto existente
    @Operation(
            summary = "Actualizar un producto existente",
            description = "Permite modificar los datos de un producto existente, incluyendo sus relaciones con marca, categoría o impuesto.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Producto actualizado correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> update(
            @Parameter(description = "ID del producto que se desea actualizar", example = "1")
            @PathVariable long id,
            @Valid @RequestBody ProductoRequestDTO dto
    ) {
        return productoService.updateProducto(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar un producto
    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto del sistema. No se recomienda eliminar productos que ya tengan compras asociadas.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del producto que se desea eliminar", example = "1")
            @PathVariable long id
    ) {
        boolean deleted = productoService.deleteProducto(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
