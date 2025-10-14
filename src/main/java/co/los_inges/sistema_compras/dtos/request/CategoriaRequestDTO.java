package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record CategoriaRequestDTO (
        @NotBlank(message = "El nombre de la categoría es obligatorio.")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
        String nombre
) {}
