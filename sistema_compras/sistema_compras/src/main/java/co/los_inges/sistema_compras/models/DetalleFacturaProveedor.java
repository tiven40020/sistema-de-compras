package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table (name = "detalles_facturas_proveedores")
public class DetalleFacturaProveedor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_factura_proveedor")
    private long idDetalleFacturaProveedor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_factura_proveedor")
    private FacturaProveedor facturaProveedor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name=  "id_producto")
    private Producto producto;

    private int cantidad;
    private double precio;

    public double getSubtotal() {
        return cantidad * precio;
    }
}
