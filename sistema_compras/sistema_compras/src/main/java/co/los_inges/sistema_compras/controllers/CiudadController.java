package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.CiudadService;
import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ciudades")

public class CiudadController {

    private final CiudadService ciudadService;

    public CiudadController (CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }
    @GetMapping
    public ResponseEntity<List<CiudadResponseDTO>> getAll() {
        List<CiudadResponseDTO> ciudades = ciudadService.getAllCiudades();
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadResponseDTO> getById(@PathVariable long id) {
        return ciudadService.getCiudadById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CiudadResponseDTO> create(@Valid @RequestBody CiudadRequestDTO dto) {
        CiudadResponseDTO nueva = ciudadService.createCiudad(dto);
        return ResponseEntity.created(URI.create("/api/ciudades/" + nueva.idCiudad()))
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody CiudadRequestDTO dto
    ) {
        return ciudadService.updateCiudad(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = ciudadService.deleteCiudad(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
