package co.los_inges.sistema_compras.controllers;
import co.los_inges.sistema_compras.models.DetalleFacturaProveedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/facturas/{idFactura}/detalles")
@Tag(name = "Detalles de Factura", description = "Operaciones sobre los detalles de una factura")
public class DetalleFacturaController {



    @GetMapping
    @Operation(summary = "Listar detalles", description = "Obtiene todos los detalles de una factura")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public List<DetalleFacturaProveedor> getAll(@PathVariable Long idFactura) {
        return new ArrayList<>();
    }

    @GetMapping("/{idDetalle}")
    @Operation(summary = "Buscar detalle por ID", description = "Obtiene un detalle de factura específico")
    @ApiResponse(responseCode = "200", description = "Detalle encontrado")
    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    public ResponseEntity<DetalleFacturaProveedor> getById(@PathVariable Long idFactura, @PathVariable Long idDetalle) {
        return  ResponseEntity.ok(new DetalleFacturaProveedor());
    }

    @PostMapping
    @Operation(summary = "Crear detalle", description = "Agrega un nuevo detalle a una factura")
    @ApiResponse(responseCode = "201", description = "Detalle creado exitosamente")
    public DetalleFacturaProveedor create(@PathVariable Long idFactura, @RequestBody DetalleFacturaProveedor detalle) {
        return  new DetalleFacturaProveedor();
    }

    @PutMapping("/{idDetalle}")
    @Operation(summary = "Actualizar detalle", description = "Modifica un detalle existente de una factura")
    @ApiResponse(responseCode = "200", description = "Detalle actualizado")
    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    public ResponseEntity<DetalleFacturaProveedor> update(@PathVariable Long idFactura, @PathVariable Long idDetalle,
      @RequestBody DetalleFacturaProveedor detalle) {
        return ResponseEntity.ok(new DetalleFacturaProveedor());
    }

    @DeleteMapping("/{idDetalle}")
    @Operation(summary = "Eliminar detalle", description = "Elimina un detalle de factura por ID")
    @ApiResponse(responseCode = "204", description = "Detalle eliminado")
    @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long idFactura, @PathVariable Long idDetalle) {
        return ResponseEntity.noContent().build();
    }
}
