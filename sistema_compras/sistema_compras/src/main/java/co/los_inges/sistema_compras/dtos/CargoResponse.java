package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CargoResponse {

    private long idCargo;
    private String nombre;
    private String descripcion;
}
