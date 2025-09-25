package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private long idProveedor;
    private String nombre;
    private String nit;
    private String email;
    private String direccion;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FacturaProveedor> listaFacturas = new HashSet<>();
}
