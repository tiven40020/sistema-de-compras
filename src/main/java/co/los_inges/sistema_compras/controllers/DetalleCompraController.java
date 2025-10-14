package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.DetalleCompraService;
import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/detalles-compra")

public class DetalleCompraController {

    private final DetalleCompraService detalleCompraService;

    public DetalleCompraController (DetalleCompraService detalleCompraService) {
        this.detalleCompraService = detalleCompraService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleCompraResponseDTO>> getAll() {
        List<DetalleCompraResponseDTO> detalles = detalleCompraService.getAllDetalles();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> getById(@PathVariable long id) {
        return detalleCompraService.getDetalleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DetalleCompraResponseDTO> create(@Valid @RequestBody DetalleCompraRequestDTO dto) {
        DetalleCompraResponseDTO nuevo = detalleCompraService.createDetalle(dto);
        return ResponseEntity.created(URI.create("/api/detalles-compra/" + nuevo.idDetalleCompra()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompraResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody DetalleCompraRequestDTO dto
    ) {
        return detalleCompraService.updateDetalle(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = detalleCompraService.deleteDetalle(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
