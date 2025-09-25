package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProveedorRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El NIT es obligatorio")
    private String nit;
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe proporcionar un email válido")
    private String email;
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;
}
