package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record DetalleCompraRequestDTO (

        @NotNull(message = "Debe especificar el ID de la compra.")
        Long idCompra,

        @NotNull(message = "Debe especificar el ID del producto.")
        Long idProducto,

        @Positive(message = "La cantidad debe ser mayor que cero.")
        int cantidad
){}
