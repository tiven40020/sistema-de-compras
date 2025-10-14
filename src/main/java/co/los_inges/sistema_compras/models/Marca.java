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
@Table (name="marcas")
@Entity
public class Marca {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id_marca")
    @EqualsAndHashCode.Include
    private long idMarca;

    @Column (nullable = false,unique = true)
    private String nombre;

    @OneToMany (mappedBy = "marca",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Producto> listaProductos = new HashSet<>();
}
