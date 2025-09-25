package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private long idProducto;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_categoria")
    private Categoria categoria;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;
    @Column(name = "stock_minimo")
    private int stockMinimo;

}
