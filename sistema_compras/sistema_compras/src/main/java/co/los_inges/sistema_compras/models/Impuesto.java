package co.los_inges.sistema_compras.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name="impuestos")
@Entity
public class Impuesto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="id_impuesto")
    private long idImpuesto;

    @Column (nullable = false)
    private String nombre;

    private double porcentaje;

    @OneToMany(mappedBy = "impuesto",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Producto> listaProductos = new HashSet<>();
}
