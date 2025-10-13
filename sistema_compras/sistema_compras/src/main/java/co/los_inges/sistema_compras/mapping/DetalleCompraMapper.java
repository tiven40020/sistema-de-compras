package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;
import co.los_inges.sistema_compras.models.DetalleCompra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface DetalleCompraMapper {

    DetalleCompraResponseDTO toDto (DetalleCompra detalleCompra);

    DetalleCompra toEntity (DetalleCompraRequestDTO detalleCompraRequestDTO);

    List<DetalleCompraResponseDTO> toDtoList (List<DetalleCompra> detalleCompraList);

    List<DetalleCompra> toEntityList (List<DetalleCompraRequestDTO> detalleCompraRequestDTOList);
}
