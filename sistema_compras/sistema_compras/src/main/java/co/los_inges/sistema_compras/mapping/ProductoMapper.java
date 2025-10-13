package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.ProductoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProductoResponseDTO;
import co.los_inges.sistema_compras.models.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                CategoriaMapper.class,
                MarcaMapper.class,
                UnidadMedidaMapper.class,
                ImpuestoMapper.class
        })
public interface ProductoMapper {

    Producto toEntity(ProductoRequestDTO dto);

    ProductoResponseDTO toDto(Producto producto);

    List<Producto> toEntityList(List<ProductoRequestDTO> dtoList);

    List<ProductoResponseDTO> toDtoList(List<Producto> productoList);
}
