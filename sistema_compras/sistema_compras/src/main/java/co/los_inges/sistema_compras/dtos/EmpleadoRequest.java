package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmpleadoRequest {

    @Positive(message = "El id del departamento debe ser un número positivo")
    private long idDepartamento;
    @Positive(message = "El id del cargo debe ser un número positivo")
    private long idCargo;
    @Positive(message = "El id del tipo de contrato debe ser un número positivo")
    private long idTipoContrato;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe proporcionar un correo válido")
    private String correo;
    private String telefono;
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private LocalDate fechaIngreso;
}
