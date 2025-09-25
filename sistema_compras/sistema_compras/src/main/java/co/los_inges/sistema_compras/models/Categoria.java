package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "categorias")
public class Categoria  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private long idCategoria;
    private String nombre;

    @OneToMany (mappedBy = "categoria", cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Producto> listaProductos = new HashSet<>();
}
