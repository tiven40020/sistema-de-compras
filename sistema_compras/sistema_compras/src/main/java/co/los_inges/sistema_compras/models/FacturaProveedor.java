package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table (name= "facturas_proveedores")
public class FacturaProveedor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name= "id_factura_proveedor")
    private long idFacturaProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name= "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="id_empleado")
    private Empleado empleado;

    private LocalDate fecha;
    private String estado;
    private String observaciones;
    private double total;

    @OneToMany(mappedBy = "facturaProveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetalleFacturaProveedor> detalles = new HashSet<>();
}
