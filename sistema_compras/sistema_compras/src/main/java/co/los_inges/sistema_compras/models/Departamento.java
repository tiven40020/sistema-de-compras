package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name= "id_departamento")
    private long idDepartamento;
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "departamento",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Empleado> empleados = new HashSet<>();
}
