package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoriaResponse {

    private long idCategoria;
    private String nombre;
}
