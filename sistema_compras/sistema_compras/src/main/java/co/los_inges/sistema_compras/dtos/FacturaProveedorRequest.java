package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FacturaProveedorRequest {

    @Positive(message = "El id del proveedor debe ser un número positivo")
    private long idProveedor;
    @Positive(message = "El id del empleado debe ser un número positivo")
    private long idEmpleado;
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fecha;
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;
    private String observaciones;
    @Positive(message = "El total debe ser mayor a 0")
    private double total;

}
