package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.ProveedorTelefonoService;
import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores-telefonos")

public class ProveedorTelefonoController {


    private final ProveedorTelefonoService proveedorTelefonoService;

    public ProveedorTelefonoController (ProveedorTelefonoService proveedorTelefonoService) {
        this.proveedorTelefonoService = proveedorTelefonoService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorTelefonoResponseDTO>> getAll() {
        List<ProveedorTelefonoResponseDTO> lista = proveedorTelefonoService.getAllProveedorTelefonos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorTelefonoResponseDTO> getById(@PathVariable long id) {
        return proveedorTelefonoService.getProveedorTelefonoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProveedorTelefonoResponseDTO> create(@Valid @RequestBody ProveedorTelefonoRequestDTO dto) {
        ProveedorTelefonoResponseDTO nuevo = proveedorTelefonoService.createProveedorTelefono(dto);
        return ResponseEntity.created(URI.create("/api/proveedores-telefonos/" + nuevo.idProveedoresTelefonos()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorTelefonoResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody ProveedorTelefonoRequestDTO dto
    ) {
        return proveedorTelefonoService.updateProveedorTelefono(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = proveedorTelefonoService.deleteProveedorTelefono(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
