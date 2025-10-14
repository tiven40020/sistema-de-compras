package co.los_inges.sistema_compras.controllers;

import co.los_inges.sistema_compras.Service.TelefonoService;
import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/telefonos")

public class TelefonoController {


    private final TelefonoService telefonoService;

    public TelefonoController (TelefonoService telefonoService) {
        this.telefonoService = telefonoService;
    }

    @GetMapping
    public ResponseEntity<List<TelefonoResponseDTO>> getAll() {
        List<TelefonoResponseDTO> telefonos = telefonoService.getAllTelefonos();
        return ResponseEntity.ok(telefonos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefonoResponseDTO> getById(@PathVariable long id) {
        return telefonoService.getTelefonoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TelefonoResponseDTO> create(@Valid @RequestBody TelefonoRequestDTO dto) {
        TelefonoResponseDTO nuevo = telefonoService.createTelefono(dto);
        return ResponseEntity.created(URI.create("/api/telefonos/" + nuevo.idTelefono()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefonoResponseDTO> update(
            @PathVariable long id,
            @Valid @RequestBody TelefonoRequestDTO dto
    ) {
        return telefonoService.updateTelefono(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = telefonoService.deleteTelefono(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
