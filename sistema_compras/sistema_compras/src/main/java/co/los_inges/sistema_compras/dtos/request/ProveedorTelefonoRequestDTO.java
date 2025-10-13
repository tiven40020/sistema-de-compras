package co.los_inges.sistema_compras.dtos.request;

import jakarta.validation.constraints.NotNull;

public record ProveedorTelefonoRequestDTO (
        @NotNull(message = "Debe especificar el ID del proveedor.")
        Long idProveedor,

        @NotNull(message = "Debe especificar el ID del teléfono.")
        Long idTelefono
) {}
