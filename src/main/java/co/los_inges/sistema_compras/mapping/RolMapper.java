package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.RolRequestDTO;
import co.los_inges.sistema_compras.dtos.response.RolResponseDTO;
import co.los_inges.sistema_compras.models.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    Rol toEntity(RolRequestDTO rolRequestDTO);

    RolResponseDTO toDto(Rol rol);

    List<RolResponseDTO> toDtoList(List<Rol> roles);

    List<Rol> toEntityList(List<RolRequestDTO> rolesRequestDTOs);
}
