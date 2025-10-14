package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import co.los_inges.sistema_compras.models.Proveedor;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CiudadMapper.class,
                TelefonoMapper.class,
                ProveedorTelefonoMapper.class
        }
)
public interface ProveedorMapper {

    Proveedor toEntity(ProveedorRequestDTO dto);

    @Mapping(target = "ciudad", ignore = false)
    ProveedorResponseDTO toDto(Proveedor proveedor);

    List<Proveedor> toEntityList(List<ProveedorRequestDTO> dtoList);

    List<ProveedorResponseDTO> toDtoList(List<Proveedor> proveedorList);
}
