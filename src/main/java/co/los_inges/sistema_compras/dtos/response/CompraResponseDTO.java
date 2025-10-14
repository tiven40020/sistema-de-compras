package co.los_inges.sistema_compras.dtos.response;

import java.sql.Date;
import java.util.List;

public record CompraResponseDTO (
        Long idCompra,
        Date fecha,
        int numeroFactura,
        ProveedorResponseDTO proveedor,
        UsuarioResponseDTO usuario,
        List<DetalleCompraResponseDTO> detallesCompra
){}
