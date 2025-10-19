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
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name= "id_usuario")
    @EqualsAndHashCode.Include
    private long idUsuario;
    @Column (length = 50, nullable = false)
    private String nombre;
    @Column (length = 100, unique = true, nullable = false)
    private String email;
    @Column ( length = 15, nullable = false)
    private String telefono;
    @Column (nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name ="id_rol", nullable = false)
    private Rol rol;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Compra> compras = new HashSet<>();
}
