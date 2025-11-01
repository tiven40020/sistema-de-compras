package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import co.los_inges.sistema_compras.models.Proveedor;
import co.los_inges.sistema_compras.models.ProveedorTelefono;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

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
    @Mapping(target = "telefonos", source = "proveedorTelefonos")
    ProveedorResponseDTO toDto(Proveedor proveedor);

    List<Proveedor> toEntityList(List<ProveedorRequestDTO> dtoList);

    List<ProveedorResponseDTO> toDtoList(List<Proveedor> proveedorList);

    default List<String> mapTelefonos(Set<ProveedorTelefono> proveedorTelefonos) {
        if (proveedorTelefonos == null) return null;
        return proveedorTelefonos.stream()
                .map(pt -> pt.getTelefono().getNumero())
                .toList();
    }
}
