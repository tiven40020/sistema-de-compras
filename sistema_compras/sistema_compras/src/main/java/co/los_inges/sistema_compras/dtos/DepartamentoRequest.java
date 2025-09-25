package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DepartamentoRequest {

    @NotBlank(message = "El nombre del departamento no puede estar vacío")
    private String nombre;
    private String descripcion;
}
