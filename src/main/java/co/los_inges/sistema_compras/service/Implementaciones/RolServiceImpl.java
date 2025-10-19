package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.dtos.request.RolRequestDTO;
import co.los_inges.sistema_compras.dtos.response.RolResponseDTO;
import co.los_inges.sistema_compras.mapping.RolMapper;
import co.los_inges.sistema_compras.models.Rol;
import co.los_inges.sistema_compras.repositories.RolRepository;
import co.los_inges.sistema_compras.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public List<RolResponseDTO> getAllRoles() {
        List<Rol> roles = rolRepository.findAll();
        return rolMapper.toDtoList(roles);
    }

    @Override
    public Optional<RolResponseDTO> getRolById(long id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDto);
    }

    @Override
    public RolResponseDTO createRol(RolRequestDTO rolRequestDTO) {
        Rol rol = rolMapper.toEntity(rolRequestDTO);
        Rol guardado = rolRepository.save(rol);
        return rolMapper.toDto(guardado);
    }

    @Override
    public Optional<RolResponseDTO> updateRol(long id, RolRequestDTO rolRequestDTO) {
        return rolRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(rolRequestDTO.nombre());
                    existing.setDescripcion(rolRequestDTO.descripcion());
                    Rol actualizado = rolRepository.save(existing);
                    return rolMapper.toDto(actualizado);
                });
    }

    @Override
    public boolean deleteRol(long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

