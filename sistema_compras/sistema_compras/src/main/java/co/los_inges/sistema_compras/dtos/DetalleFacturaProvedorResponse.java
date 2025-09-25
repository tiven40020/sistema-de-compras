package co.los_inges.sistema_compras.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DetalleFacturaProvedorResponse {


    private long idDetalleFacturaProveedor;
    private Long idFacturaProveedor;
    private Long idProducto;
    private int cantidad;
    private double precio;
}
