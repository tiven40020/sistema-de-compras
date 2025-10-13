package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;
import co.los_inges.sistema_compras.models.Ciudad;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProveedorMapper.class})
public interface CiudadMapper {

    Ciudad toEntity (CiudadRequestDTO ciudadRequestDTO);

    CiudadResponseDTO toDto (Ciudad ciudad);

    List<Ciudad> toCiudadList(List<CiudadRequestDTO> ciudadRequestDTOList);

    List<CiudadResponseDTO> toDtoList(List<Ciudad> ciudadList);
}
