package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.models.Proveedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "Operaciones sobre los proveedores")
public class ProveedorController {


    @GetMapping
    @Operation(summary = "Listar proveedores", description = "Obtiene todos los proveedores registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<Proveedor> getAll() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar proveedor por ID", description = "Obtiene un proveedor por su identificador único")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new Proveedor());
    }

    @PostMapping
    @Operation(summary = "Crear proveedor", description = "Registra un nuevo proveedor")
    @ApiResponse(responseCode = "201", description = "Proveedor creado exitosamente")
    public Proveedor create(@RequestBody Proveedor proveedor) {
        return new Proveedor();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Modifica los datos de un proveedor existente")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(new Proveedor());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor por ID")
    @ApiResponse(responseCode = "204", description = "Proveedor eliminado")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
