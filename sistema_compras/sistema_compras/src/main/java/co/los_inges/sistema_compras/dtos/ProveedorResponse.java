package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProveedorResponse {

    private long idProveedor;
    private String nombre;
    private String nit;
    private String email;
    private String direccion;
}
