package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DetalleFacturaProvedorRequest {

    @NotNull(message = "El id de la factura proveedor es obligatorio")
    @Positive(message = "El id de la factura proveedor debe ser positivo")
    private Long idFacturaProveedor;
    @NotNull(message = "El id del producto es obligatorio")
    @Positive(message = "El id del producto debe ser positivo")
    private Long idProducto;
    @Positive(message = "La cantidad debe ser mayor a 0")
    private int cantidad;
    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;
}
