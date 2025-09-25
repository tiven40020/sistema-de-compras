package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table (name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_empleado")
    private long idEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_contrato", nullable = false)
    private TipoContrato tipoContrato;

    private String nombre;
    private String apellido;

    @Column (name = "numero_documento")
    private String numeroDocumento;

    private String correo;
    private String telefono;
    private String direccion;

    @Column (name = "fecha_ingreso")
    private LocalDate fechaIngreso;
}
