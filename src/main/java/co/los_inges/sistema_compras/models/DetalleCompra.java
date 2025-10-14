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
@Table ( name = "detalle_compra")
public class DetalleCompra {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column ( name = "id_detalle_compra")
    @EqualsAndHashCode.Include
    private Long idDetalleCompra;

    @ManyToOne ( fetch =  FetchType.LAZY)
    @JoinColumn (name = "id_compra")
    private Compra compra;

    @ManyToOne ( fetch = FetchType.LAZY)
    @JoinColumn (name = "id_produto")
    private Producto producto;

    private int cantidad;


}
