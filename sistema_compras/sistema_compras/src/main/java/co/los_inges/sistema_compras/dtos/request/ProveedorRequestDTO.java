package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;
import java.util.List;

public record ProveedorRequestDTO (
        @NotBlank(message = "El nombre del proveedor es obligatorio.")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres.")
        String nombre,

        @NotNull(message = "Debe especificar el ID de la ciudad.")
        Long idCiudad,

        @NotBlank(message = "La dirección es obligatoria.")
        @Size(max = 100, message = "La dirección no puede superar los 100 caracteres.")
        String direccion,

        @NotBlank(message = "El correo electrónico es obligatorio.")
        @Email(message = "El correo electrónico no tiene un formato válido.")
        String email,

        boolean estado,

        @NotEmpty(message = "Debe proporcionar al menos un número de teléfono.")
        List<@NotBlank(message = "El número de teléfono no puede estar vacío.")
        @Pattern(regexp = "^[0-9+\\-\\s]{7,15}$", message = "El número de teléfono tiene un formato inválido.")
                String> telefonos
) {}



