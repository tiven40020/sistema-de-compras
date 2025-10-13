package co.los_inges.sistema_compras.Service.Implementaciones;

import co.los_inges.sistema_compras.Service.MarcaService;
import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
import co.los_inges.sistema_compras.mapping.MarcaMapper;
import co.los_inges.sistema_compras.models.Marca;
import co.los_inges.sistema_compras.repositories.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {


    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public List<MarcaResponseDTO> getAllMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return marcaMapper.toDtoList(marcas);
    }

    @Override
    public Optional<MarcaResponseDTO> getMarcaById(long id) {
        return marcaRepository.findById(id)
                .map(marcaMapper::toDto);
    }

    @Override
    public MarcaResponseDTO createMarca(MarcaRequestDTO dto) {
        Marca marca = marcaMapper.toEntity(dto);
        Marca saved = marcaRepository.save(marca);
        return marcaMapper.toDto(saved);
    }

    @Override
    public Optional<MarcaResponseDTO> updateMarca(long id, MarcaRequestDTO dto) {
        return marcaRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    Marca updated = marcaRepository.save(existing);
                    return marcaMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteMarca(long id) {
        if (!marcaRepository.existsById(id)) return false;
        marcaRepository.deleteById(id);
        return true;
    }
}
