package co.los_inges.sistema_compras.dtos;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmpleadoResponse {

    private long idEmpleado;
    private long idDepartamento;
    private long idCargo;
    private long idTipoContrato;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDate fechaIngreso;
}
