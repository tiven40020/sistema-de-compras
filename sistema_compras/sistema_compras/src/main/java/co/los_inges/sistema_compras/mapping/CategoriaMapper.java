package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;
import co.los_inges.sistema_compras.models.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface CategoriaMapper {

    Categoria toEntity(CategoriaRequestDTO categoriaRequestDTO);

    CategoriaResponseDTO toDto(Categoria categoria);

    List<CategoriaResponseDTO> toDtoList(List<Categoria> categorias);

    List<Categoria> toEntityList(List<CategoriaRequestDTO> categoriaRequestDTOS);
}
