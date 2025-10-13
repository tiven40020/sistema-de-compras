package co.los_inges.sistema_compras.dtos.response;

import java.util.List;

public record TelefonoResponseDTO (
        Long idTelefono,
        String numero,
        List<ProveedorResponseDTO> proveedores
){}
