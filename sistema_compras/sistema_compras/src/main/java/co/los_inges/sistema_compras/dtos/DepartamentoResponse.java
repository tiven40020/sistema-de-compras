package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DepartamentoResponse {

    private long idDepartamento;
    private String nombre;
    private String descripcion;
}
