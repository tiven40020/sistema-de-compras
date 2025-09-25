package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TipoContratoResponse {

    private long idTipoContrato;
    private String nombre;
    private String duracion;
}
