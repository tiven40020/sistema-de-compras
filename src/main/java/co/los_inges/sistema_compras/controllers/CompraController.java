package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.CompraService;
import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/compras")

public class CompraController {

    private final CompraService compraService;

    public CompraController (CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> getAll() {
        List<CompraResponseDTO> compras = compraService.getAllCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> getById(@PathVariable long id) {
        return compraService.getCompraById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CompraResponseDTO> create(@Valid @RequestBody CompraRequestDTO dto) {
        CompraResponseDTO nuevaCompra = compraService.createCompra(dto);
        return ResponseEntity.created(URI.create("/api/compras/" + nuevaCompra.idCompra()))
                .body(nuevaCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody CompraRequestDTO dto
    ) {
        return compraService.updateCompra(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = compraService.deleteCompra(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
