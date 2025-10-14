package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;
import co.los_inges.sistema_compras.models.Telefono;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefonoMapper {

    Telefono toEntity(TelefonoRequestDTO dto);

    TelefonoResponseDTO toDto(Telefono telefono);

    List<Telefono> toEntityList(List<TelefonoRequestDTO> dtoList);

    List<TelefonoResponseDTO> toDtoList(List<Telefono> telefonoList);
}
