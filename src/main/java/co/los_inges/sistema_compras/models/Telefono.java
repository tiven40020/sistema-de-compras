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
@Table (name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_telefono")
    @EqualsAndHashCode.Include
    private long idTelefono;
    @Column(length = 15, nullable = false)
    private String numero;

    @OneToMany(mappedBy = "telefono", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProveedorTelefono> proveedorTelefonos = new HashSet<>();
}
