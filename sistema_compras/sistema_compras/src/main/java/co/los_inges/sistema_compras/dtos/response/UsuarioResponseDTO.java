package co.los_inges.sistema_compras.dtos.response;

import java.util.List;

public record UsuarioResponseDTO (
        Long idUsuario,
        String nombre,
        String email,
        String telefono,
        List<CompraResponseDTO> compras
) {}
