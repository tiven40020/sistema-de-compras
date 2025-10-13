package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "compras")
public class Compra {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_compra")
    private long idCompra;
    private Date fecha;
    @Column (name = "num_factura", length = 50)
    private int numeroFactura;
    @ManyToOne ( fetch =  FetchType.LAZY)
    @JoinColumn (name = "id_proveedor")
    private Proveedor proveedor;
    @ManyToOne ( fetch = FetchType.LAZY)
    @JoinColumn (name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleCompra> detallesCompra = new HashSet<>();
}
