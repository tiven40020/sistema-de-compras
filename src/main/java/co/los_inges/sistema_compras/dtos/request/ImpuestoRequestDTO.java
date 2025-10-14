package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record ImpuestoRequestDTO (
        @NotBlank(message = "El nombre del impuesto es obligatorio.")
        @Size(min = 2, max = 50, message = "El nombre del impuesto debe tener entre 3 y 50 caracteres.")
        String nombre,

        @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje no puede ser negativo.")
        @DecimalMax(value = "100.0", message = "El porcentaje no puede superar el 100%.")
        double porcentaje
){}
