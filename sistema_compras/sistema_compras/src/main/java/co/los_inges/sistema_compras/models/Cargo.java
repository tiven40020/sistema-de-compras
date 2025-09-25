package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table (name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private long idCargo;
    private String nombre;
    private String descripcion;

    @OneToMany (mappedBy = "cargo", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Empleado> empleados = new HashSet<>();
}
