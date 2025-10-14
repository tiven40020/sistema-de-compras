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
@Table(name = "proveedores_telefonos")
public class ProveedorTelefono {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedores_telefonos")
    @EqualsAndHashCode.Include
    private long idProveedoresTelefonos;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_telefono", nullable = false)
    private Telefono telefono;

}
