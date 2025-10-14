package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Table (name="impuestos")
@Entity
public class Impuesto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id_impuesto")
    @EqualsAndHashCode.Include
    private long idImpuesto;

    @Column (nullable = false)
    private String nombre;

    private double porcentaje;

    @OneToMany(mappedBy = "impuesto",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Producto> listaProductos = new HashSet<>();
}
