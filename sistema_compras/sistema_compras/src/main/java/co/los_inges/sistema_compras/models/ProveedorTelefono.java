package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "proveedores_telefonos")
public class ProveedorTelefono {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedores_telefonos")
    private long idProveedoresTelefonos;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_telefono", nullable = false)
    private Telefono telefono;

}
