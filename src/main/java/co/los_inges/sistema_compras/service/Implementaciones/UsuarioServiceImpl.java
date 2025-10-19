package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.UsuarioService;
import co.los_inges.sistema_compras.dtos.request.UsuarioRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UsuarioResponseDTO;
import co.los_inges.sistema_compras.mapping.UsuarioMapper;
import co.los_inges.sistema_compras.models.Usuario;
import co.los_inges.sistema_compras.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioResponseDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }

    @Override
    public Optional<UsuarioResponseDTO> getUsuarioById(long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto);
    }

    @Override
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(saved);
    }

    @Override
    public Optional<UsuarioResponseDTO> updateUsuario(long id, UsuarioRequestDTO dto) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    existing.setEmail(dto.email());
                    existing.setTelefono(dto.telefono());

                    if (dto.password() != null && !dto.password().isBlank()) {
                        existing.setPassword(passwordEncoder.encode(dto.password()));
                    }

                    Usuario updated = usuarioRepository.save(existing);
                    return usuarioMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteUsuario(long id) {
        if (!usuarioRepository.existsById(id)) return false;
        usuarioRepository.deleteById(id);
        return true;
    }
}
