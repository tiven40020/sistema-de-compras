package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;
import co.los_inges.sistema_compras.models.Impuesto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface ImpuestoMapper {

    Impuesto toEntity (ImpuestoRequestDTO impuestoRequestDTO);

    ImpuestoResponseDTO toDto (Impuesto impuesto);

    List<Impuesto> toEntityList (List<ImpuestoRequestDTO> impuestoRequestDTOList);

    List<ImpuestoResponseDTO> toDtoList (List<Impuesto> impuestoList);
}
