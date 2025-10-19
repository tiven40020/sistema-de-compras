package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name="roles")
public class Rol {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    @Column (name ="id_rol")
    private long idRol;
    @Column (length = 50,unique = true, nullable = false)
    private String nombre;
    private String descripcion;
}
