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
@Entity
@Table (name = "categorias")
public class Categoria  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    @EqualsAndHashCode.Include
    private long idCategoria;
    @Column (nullable = false,unique = true)
    private String nombre;

    @OneToMany (mappedBy = "categoria", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Producto> listaProductos = new HashSet<>();
}
