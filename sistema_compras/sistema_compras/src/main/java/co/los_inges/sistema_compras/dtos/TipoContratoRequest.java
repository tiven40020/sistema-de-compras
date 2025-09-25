package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TipoContratoRequest {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "La duración no puede estar vacía")
    private String duracion;
}
