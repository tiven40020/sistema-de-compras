package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;
import co.los_inges.sistema_compras.models.ProveedorTelefono;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ProveedorMapper.class, TelefonoMapper.class}
)
public interface ProveedorTelefonoMapper {

    ProveedorTelefono toEntity(ProveedorTelefonoRequestDTO dto);

    ProveedorTelefonoResponseDTO toDto(ProveedorTelefono proveedorTelefono);

    List<ProveedorTelefono> toEntityList(List<ProveedorTelefonoRequestDTO> dtoList);

    List<ProveedorTelefonoResponseDTO> toDtoList(List<ProveedorTelefono> proveedorTelefonoList);
}
