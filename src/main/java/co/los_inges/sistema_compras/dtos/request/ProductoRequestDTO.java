package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record ProductoRequestDTO (
        @NotBlank(message = "El nombre del producto es obligatorio.")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres.")
        String nombre,

        @NotNull(message = "Debe especificar el ID de la categoría.")
        Long idCategoria,

        @NotNull(message = "Debe especificar el ID de la marca.")
        Long idMarca,

        @NotNull(message = "Debe especificar el ID de la unidad de medida.")
        Long idUnidadMedida,

        @Positive(message = "La cantidad por unidad de medida debe ser mayor que cero.")
        int cantidadUnidadesMedidas,

        @NotNull(message = "Debe especificar el ID del impuesto.")
        Long idImpuesto,

        @PositiveOrZero(message = "El precio no puede ser negativo.")
        double precio,

        @PositiveOrZero(message = "El stock no puede ser negativo.")
        int stock,

        boolean estado
){}
