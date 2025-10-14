package co.los_inges.sistema_compras.Service.Implementaciones;

import co.los_inges.sistema_compras.Service.UnidadMedidaService;
import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;
import co.los_inges.sistema_compras.mapping.UnidadMedidaMapper;
import co.los_inges.sistema_compras.models.UnidadMedida;
import co.los_inges.sistema_compras.repositories.UnidadMedidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;
    private final UnidadMedidaMapper unidadMedidaMapper;

    @Override
    public List<UnidadMedidaResponseDTO> getAllUnidadesMedida() {
        List<UnidadMedida> unidades = unidadMedidaRepository.findAll();
        return unidadMedidaMapper.toDtoList(unidades);
    }

    @Override
    public Optional<UnidadMedidaResponseDTO> getUnidadMedidaById(long id) {
        return unidadMedidaRepository.findById(id)
                .map(unidadMedidaMapper::toDto);
    }

    @Override
    public UnidadMedidaResponseDTO createUnidadMedida(UnidadMedidaRequestDTO dto) {
        UnidadMedida unidad = unidadMedidaMapper.toEntity(dto);
        UnidadMedida saved = unidadMedidaRepository.save(unidad);
        return unidadMedidaMapper.toDto(saved);
    }

    @Override
    public Optional<UnidadMedidaResponseDTO> updateUnidadMedida(long id, UnidadMedidaRequestDTO dto) {
        return unidadMedidaRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    UnidadMedida updated = unidadMedidaRepository.save(existing);
                    return unidadMedidaMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteUnidadMedida(long id) {
        if (!unidadMedidaRepository.existsById(id)) return false;
        unidadMedidaRepository.deleteById(id);
        return true;
    }
}
