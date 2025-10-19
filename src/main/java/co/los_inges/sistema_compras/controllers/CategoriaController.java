package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.CategoriaService;
import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;
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
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Endpoints para la gestión de categorías de productos")

public class CategoriaController {

    private final CategoriaService categoriaService;


    // GET: obtener todas las categorías
    @Operation(
            summary = "Obtener todas las categorías",
            description = "Devuelve una lista con todas las categorías registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoriaResponseDTO.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
        List<CategoriaResponseDTO> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    // GET: obtener una categoría por ID
    @Operation(
            summary = "Obtener una categoría por ID",
            description = "Busca y devuelve una categoría existente por su identificador.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoriaResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable long id) {
        return categoriaService.getCategoriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: crear una nueva categoría
    @Operation(
            summary = "Crear una nueva categoría",
            description = "Crea una categoría en el sistema a partir de los datos enviados.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoriaResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@Valid @RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO nueva = categoriaService.createCategoria(dto);
        return ResponseEntity.created(URI.create("/api/categorias/" + nueva.idCategoria()))
                .body(nueva);
    }

    // PUT: actualizar una categoría
    @Operation(
            summary = "Actualizar una categoría existente",
            description = "Actualiza los datos de una categoría existente por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody CategoriaRequestDTO dto
    ) {
        return categoriaService.updateCategoria(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: eliminar una categoría
    @Operation(
            summary = "Eliminar una categoría",
            description = "Elimina una categoría del sistema según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = categoriaService.deleteCategoria(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
