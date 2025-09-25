package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "tipos_contratos")
public class TipoContrato {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contrato")
    private long idTipoContrato;
    private String nombre;
    private String duracion;

    @OneToMany(mappedBy = "tipoContrato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Empleado> empleados = new HashSet<>();
}
