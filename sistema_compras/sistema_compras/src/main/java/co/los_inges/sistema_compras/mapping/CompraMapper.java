package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;
import co.los_inges.sistema_compras.models.Compra;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ProveedorMapper.class, UsuarioMapper.class, DetalleCompraMapper.class}
)
public interface CompraMapper {

    Compra toEntity (CompraRequestDTO compraRequestDTO);

    CompraResponseDTO toDto(Compra compra);

    List<Compra> toListEntity (List<CompraRequestDTO> compraRequestDTOList);

    List<CompraResponseDTO> toDtoList (List<Compra>compraList);
}
