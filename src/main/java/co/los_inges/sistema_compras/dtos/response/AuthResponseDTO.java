package co.los_inges.sistema_compras.dtos.response;

public record AuthResponseDTO(
        String accessToken,
        String tokenType,
        long expiresInSeconds
) {}

