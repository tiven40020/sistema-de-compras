package co.los_inges.sistema_compras.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductoResponse {

    private long idProducto;
    private long idCategoria;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;
    private int stockMinimo;
}
