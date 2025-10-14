package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;
import java.sql.Date;
import java.util.List;

public record CompraRequestDTO (

        @NotNull(message = "La fecha de la compra es obligatoria.")
        Date fecha,

        @Positive(message = "El número de factura debe ser mayor que cero.")
        int numeroFactura,

        @NotNull(message = "Debe especificar el ID del proveedor.")
        Long idProveedor,

        @NotNull(message = "Debe especificar el ID del usuario que realiza la compra.")
        Long idUsuario,

        List<DetalleCompraRequestDTO> detallesCompra
) {}
