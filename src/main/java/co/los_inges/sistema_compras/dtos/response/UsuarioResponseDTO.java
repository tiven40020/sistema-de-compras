package co.los_inges.sistema_compras.dtos.response;

public record UsuarioResponseDTO (
        long idUsuario,
        String nombre,
        String email,
        RolResponseDTO rol,
        String telefono
) {}
