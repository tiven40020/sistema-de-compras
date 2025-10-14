package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table (name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    @EqualsAndHashCode.Include
    private long idProducto;
    @Column (nullable = false)
    private String nombre;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_categoria")
    private Categoria categoria;

    @ManyToOne ( fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_marca")
    private Marca marca;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn ( name= "id_unidad_medida")
    private UnidadMedida unidadMedida;

    @Column ( name= "cantidad_unidades_medida")
    private int cantidadUnidadesMedidas;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name= "id_impuesto")
    private Impuesto impuesto;

    private double precio;
    private int stock;
    private boolean estado;

}
