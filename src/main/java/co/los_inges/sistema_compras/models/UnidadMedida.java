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
@Table (name = "unidades_de_medida")
@Entity
public class UnidadMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_unidad_medida")
    @EqualsAndHashCode.Include
    private long idUnidadMedida;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "unidadMedida",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Producto> listaProducto = new HashSet<>();

}
