package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.ImpuestoService;
import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;
import co.los_inges.sistema_compras.mapping.ImpuestoMapper;
import co.los_inges.sistema_compras.models.Impuesto;
import co.los_inges.sistema_compras.repositories.ImpuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImpuestoServiceImpl implements ImpuestoService {

    private final ImpuestoRepository impuestoRepository;
    private final ImpuestoMapper impuestoMapper;

    @Override
    public List<ImpuestoResponseDTO> getAllImpuestos() {
        List<Impuesto> impuestos = impuestoRepository.findAll();
        return impuestoMapper.toDtoList(impuestos);
    }

    @Override
    public Optional<ImpuestoResponseDTO> getImpuestoById(long id) {
        return impuestoRepository.findById(id)
                .map(impuestoMapper::toDto);
    }

    @Override
    public ImpuestoResponseDTO createImpuesto(ImpuestoRequestDTO dto) {
        Impuesto impuesto = impuestoMapper.toEntity(dto);
        Impuesto saved = impuestoRepository.save(impuesto);
        return impuestoMapper.toDto(saved);
    }

    @Override
    public Optional<ImpuestoResponseDTO> updateImpuesto(long id, ImpuestoRequestDTO dto) {
        return impuestoRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    existing.setPorcentaje(dto.porcentaje());
                    Impuesto updated = impuestoRepository.save(existing);
                    return impuestoMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteImpuesto(long id) {
        if (!impuestoRepository.existsById(id)) return false;
        impuestoRepository.deleteById(id);
        return true;
    }
}
