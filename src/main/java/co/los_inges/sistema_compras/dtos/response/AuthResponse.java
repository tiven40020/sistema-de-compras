package co.los_inges.sistema_compras.dtos.response;

public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresInSeconds
) {}

