package co.los_inges.sistema_compras.dtos.response;

public record ProveedorTelefonoResponseDTO(
        Long idProveedoresTelefonos,
        ProveedorResponseDTO proveedor,
        TelefonoResponseDTO telefono
){}
