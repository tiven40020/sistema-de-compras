package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO (
        @NotBlank(message = "El nombre del usuario es obligatorio.")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
        String nombre,

        @NotBlank(message = "El correo electrónico es obligatorio.")
        @Email(message = "El formato del correo electrónico no es válido.")
        @Size(max = 100, message = "El correo no puede superar los 100 caracteres.")
        String email,

        @NotBlank(message = "El número de teléfono es obligatorio.")
        @Pattern(
                regexp = "^[0-9+\\-\\s]{2,15}$",
                message = "El número de teléfono tiene un formato inválido. Solo se permiten dígitos, espacios, '+' y '-'."
        )
        String telefono,

        @NotBlank(message = "La contraseña es obligatoria.")
        @Size(min = 2, message = "La contraseña debe tener al menos 6 caracteres.")
        String password
){}
