package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.ImpuestoService;
import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/impuestos")

public class ImpuestoController {

    private final ImpuestoService impuestoService;

    public ImpuestoController (ImpuestoService impuestoService) {
        this.impuestoService = impuestoService;
    }

    @GetMapping
    public ResponseEntity<List<ImpuestoResponseDTO>> getAll() {
        List<ImpuestoResponseDTO> impuestos = impuestoService.getAllImpuestos();
        return ResponseEntity.ok(impuestos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoResponseDTO> getById(@PathVariable long id) {
        return impuestoService.getImpuestoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ImpuestoResponseDTO> create(@Valid @RequestBody ImpuestoRequestDTO dto) {
        ImpuestoResponseDTO nuevo = impuestoService.createImpuesto(dto);
        return ResponseEntity.created(URI.create("/api/impuestos/" + nuevo.idImpuesto()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody ImpuestoRequestDTO dto
    ) {
        return impuestoService.updateImpuesto(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = impuestoService.deleteImpuesto(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
