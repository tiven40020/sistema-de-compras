package co.los_inges.sistema_compras.mapping;

import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import co.los_inges.sistema_compras.models.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompraMapper.class})
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDto(Usuario usuario);

    List<Usuario> toEntityList(List<UsuarioRequestDTO> dtoList);

    List<UsuarioResponseDTO> toDtoList(List<Usuario> usuarioList);
}
