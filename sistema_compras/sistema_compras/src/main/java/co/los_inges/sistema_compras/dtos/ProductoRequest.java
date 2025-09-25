package co.los_inges.sistema_compras.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductoRequest {

    @Positive(message = "La categoría debe ser un número positivo")
    private long idCategoria;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El descripción no puede estar vacía")
    private String descripcion;
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;
    @Positive(message = "El precio debe ser mayor a 0")
    private double precio;
    @PositiveOrZero(message = "El stock mínimo no puede ser negativo")
    private int stockMinimo;
}
