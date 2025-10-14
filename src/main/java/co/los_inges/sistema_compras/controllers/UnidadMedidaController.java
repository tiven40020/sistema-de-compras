package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.UnidadMedidaService;
import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")

public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    public UnidadMedidaController (UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public ResponseEntity<List<UnidadMedidaResponseDTO>> getAll() {
        List<UnidadMedidaResponseDTO> unidades = unidadMedidaService.getAllUnidadesMedida();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> getById(@PathVariable long id) {
        return unidadMedidaService.getUnidadMedidaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnidadMedidaResponseDTO> create(@Valid @RequestBody UnidadMedidaRequestDTO dto) {
        UnidadMedidaResponseDTO nueva = unidadMedidaService.createUnidadMedida(dto);
        return ResponseEntity.created(URI.create("/api/unidades-medida/" + nueva.idUnidadMedida()))
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody UnidadMedidaRequestDTO dto
    ) {
        return unidadMedidaService.updateUnidadMedida(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = unidadMedidaService.deleteUnidadMedida(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
