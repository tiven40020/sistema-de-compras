package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name= "id_usuario")
    private long idUsuario;
    @Column (length = 50, nullable = false)
    private String nombre;
    @Column (length = 100, nullable = false)
    private String email;
    @Column ( length = 15, nullable = false)
    private String telefono;
    @Column (nullable = false)
    private String password;

    @OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Compra> compras = new HashSet<>();
}
