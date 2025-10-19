package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.service.MarcaService;
import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")

public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController (MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> getAll() {
        List<MarcaResponseDTO> marcas = marcaService.getAllMarcas();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> getById(@PathVariable long id) {
        return marcaService.getMarcaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> create(@Valid @RequestBody MarcaRequestDTO dto) {
        MarcaResponseDTO nueva = marcaService.createMarca(dto);
        return ResponseEntity.created(URI.create("/api/marcas/" + nueva.idMarca()))
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        return marcaService.updateMarca(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = marcaService.deleteMarca(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
