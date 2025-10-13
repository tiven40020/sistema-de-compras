package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.ProveedorService;
import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController (ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponseDTO>> getAll() {
        List<ProveedorResponseDTO> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> getById(@PathVariable long id) {
        return proveedorService.getProveedorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorResponseDTO> create(@Valid @RequestBody ProveedorRequestDTO dto) {
        ProveedorResponseDTO nuevo = proveedorService.createProveedor(dto);
        return ResponseEntity.created(URI.create("/api/proveedores/" + nuevo.idProveedor()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody ProveedorRequestDTO dto
    ) {
        return proveedorService.updateProveedor(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = proveedorService.deleteProveedor(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
