package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
import co.los_inges.sistema_compras.models.Marca;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface MarcaMapper {

    Marca toEntity(MarcaRequestDTO dto);

    MarcaResponseDTO toDto(Marca marca);

    List<Marca> toEntityList(List<MarcaRequestDTO> dtoList);

    List<MarcaResponseDTO> toDtoList(List<Marca> marcaList);
}
