package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioResponseDTO> getAllUsuarios();

    Optional<UsuarioResponseDTO> getUsuarioById(long id);

    UsuarioResponseDTO createUsuario(UsuarioRequestDTO usuarioRequestDTO);

    Optional<UsuarioResponseDTO> updateUsuario(long id, UsuarioRequestDTO usuarioRequestDTO);

    boolean deleteUsuario(long id);
}
