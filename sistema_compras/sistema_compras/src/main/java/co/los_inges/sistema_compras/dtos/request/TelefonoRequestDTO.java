package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record TelefonoRequestDTO (
        @NotBlank(message = "El número de teléfono es obligatorio.")
        @Pattern(
                regexp = "^[0-9+\\-\\s]{7,15}$",
                message = "El número de teléfono tiene un formato inválido. Solo se permiten dígitos, espacios, '+' y '-'."
        )
        String numero
){}
