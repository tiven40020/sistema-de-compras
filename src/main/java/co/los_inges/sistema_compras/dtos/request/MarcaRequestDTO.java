package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record MarcaRequestDTO (
        @NotBlank(message = "El nombre de la marca es obligatorio.")
        @Size(min = 2, max = 50, message = "El nombre de la marca debe tener entre 2 y 50 caracteres.")
        String nombre
){}
