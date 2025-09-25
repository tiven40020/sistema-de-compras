package co.los_inges.sistema_compras.controllers;
import co.los_inges.sistema_compras.models.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Operaciones sobre las categorías de productos")
public class CategoriaController {



    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<Categoria> getAll() {
        List<Categoria>lista = new ArrayList<>();
        return lista;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoría por ID", description = "Obtiene una categoría por su ID")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<Categoria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new Categoria());
    }

    @PostMapping
    @Operation(summary = "Crear categoría", description = "Registra una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente")
    public Categoria create(@RequestBody Categoria categoria) {
        return new Categoria();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría", description = "Modifica los datos de una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(new Categoria());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por ID")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
