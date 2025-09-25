package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.models.Producto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones sobre los productos")
public class ProductoController {

    @GetMapping
    @Operation(summary = "Listar productos", description = "Obtiene todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<Producto> getAll() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene un producto por su identificador")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new Producto());
    }

    @GetMapping("/categoria/{idCategoria}")
    @Operation(summary = "Listar productos por categoría", description = "Obtiene los productos de una categoría específica")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<Producto> getByCategoria(@PathVariable Long idCategoria) {
        return new ArrayList<>();
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Registra un nuevo producto")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    public Producto create(@RequestBody Producto producto) {
        return new Producto();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Modifica un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto actualizado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(new Producto());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID")
    @ApiResponse(responseCode = "204", description = "Producto eliminado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
