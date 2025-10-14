package co.los_inges.sistema_compras.dtos.response;

public record ProductoResponseDTO (
        Long idProducto,
        String nombre,
        CategoriaResponseDTO categoria,
        MarcaResponseDTO marca,
        UnidadMedidaResponseDTO unidadMedida,
        int cantidadUnidadesMedidas,
        ImpuestoResponseDTO impuesto,
        double precio,
        int stock,
        boolean estado
) {}
