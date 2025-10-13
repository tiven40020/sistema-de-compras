package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;
import co.los_inges.sistema_compras.models.UnidadMedida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface UnidadMedidaMapper {

    UnidadMedida toEntity(UnidadMedidaRequestDTO dto);

    UnidadMedidaResponseDTO toDto(UnidadMedida unidadMedida);

    List<UnidadMedida> toEntityList(List<UnidadMedidaRequestDTO> dtoList);

    List<UnidadMedidaResponseDTO> toDtoList(List<UnidadMedida> unidadMedidaList);
}
