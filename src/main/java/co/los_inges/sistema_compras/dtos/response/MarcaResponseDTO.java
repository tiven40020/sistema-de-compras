package co.los_inges.sistema_compras.dtos.response;

import java.util.List;

public record MarcaResponseDTO (
        Long idMarca,
        String nombre
        //List<ProductoResponseDTO> productos
){}
