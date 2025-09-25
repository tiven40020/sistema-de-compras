package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CargoRequest {

    @NotBlank(message = "El nombre del cargo no puede estar vacío")
    private String nombre;
    private String descripcion;
}
