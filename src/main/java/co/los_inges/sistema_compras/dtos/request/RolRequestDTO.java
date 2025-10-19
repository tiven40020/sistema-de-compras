package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RolRequestDTO (
        @NotBlank(message = "El nombre del rol es obligatorio.")
        @Size(min = 2, max = 50, message = "El nombre del rol debe tener entre 2 y 50 caracteres.")
        String nombre,
        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres.")
        String descripcion) {
}
