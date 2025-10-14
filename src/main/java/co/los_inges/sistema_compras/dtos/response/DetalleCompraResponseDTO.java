package co.los_inges.sistema_compras.dtos.response;

public record DetalleCompraResponseDTO (
        Long idDetalleCompra,
        ProductoResponseDTO producto,
        int cantidad,
        double subtotal

) {}
