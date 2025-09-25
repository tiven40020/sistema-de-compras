package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoriaRequest {

    @NotBlank(message = "El nombre de la categorias no puede estar vacío")
    private String nombre;
}
