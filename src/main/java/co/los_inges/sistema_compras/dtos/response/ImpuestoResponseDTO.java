package co.los_inges.sistema_compras.dtos.response;

import java.util.List;

public record ImpuestoResponseDTO (
        Long idImpuesto,
        String nombre,
        double porcentaje
){}
