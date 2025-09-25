package co.los_inges.sistema_compras.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FacturaProveedorResponse {

    private long idFacturaProveedor;
    private long idProveedor;
    private long idEmpleado;
    private LocalDate fecha;
    private String estado;
    private String observaciones;
    private double total;

}
