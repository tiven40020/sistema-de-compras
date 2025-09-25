package co.los_inges.sistema_compras.controllers;
import co.los_inges.sistema_compras.models.FacturaProveedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Facturas", description = "Operaciones sobre facturas de proveedores")
public class FacturaProveedorController {

    @GetMapping
    @Operation(summary = "Listar facturas", description = "Obtiene todas las facturas de proveedores")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<FacturaProveedor> getAll() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar factura por ID", description = "Obtiene una factura usando su identificador")
    @ApiResponse(responseCode = "200", description = "Factura encontrada")
    @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    public ResponseEntity<FacturaProveedor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new FacturaProveedor());
    }

    @GetMapping("/proveedor/{idProveedor}")
    @Operation(summary = "Listar facturas por proveedor", description = "Obtiene todas las facturas de un proveedor específico")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<FacturaProveedor> getByProveedor(@PathVariable Long idProveedor) {
        return new ArrayList<>();
    }

    @PostMapping
    @Operation(summary = "Crear factura", description = "Registra una nueva factura de proveedor")
    @ApiResponse(responseCode = "201", description = "Factura creada exitosamente")
    public FacturaProveedor create(@RequestBody FacturaProveedor factura) {
        return new FacturaProveedor();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar factura", description = "Modifica una factura existente")
    @ApiResponse(responseCode = "200", description = "Factura actualizada")
    @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    public ResponseEntity<FacturaProveedor> update(@PathVariable Long id, @RequestBody FacturaProveedor factura) {
        return ResponseEntity.ok(new FacturaProveedor());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura", description = "Elimina una factura por ID")
    @ApiResponse(responseCode = "204", description = "Factura eliminada")
    @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
