package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.CiudadService;
import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;
import co.los_inges.sistema_compras.mapping.CiudadMapper;
import co.los_inges.sistema_compras.models.Ciudad;
import co.los_inges.sistema_compras.repositories.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CiudadServiceImpl implements CiudadService {


    private final CiudadRepository ciudadRepository;
    private final CiudadMapper ciudadMapper;

    @Override
    public List<CiudadResponseDTO> getAllCiudades() {
        List<Ciudad> ciudades = ciudadRepository.findAll();
        return ciudadMapper.toDtoList(ciudades);
    }

    @Override
    public Optional<CiudadResponseDTO> getCiudadById(long id) {
        return ciudadRepository.findById(id)
                .map(ciudadMapper::toDto);
    }

    @Override
    public CiudadResponseDTO createCiudad(CiudadRequestDTO dto) {
        Ciudad ciudad = ciudadMapper.toEntity(dto);
        Ciudad saved = ciudadRepository.save(ciudad);
        return ciudadMapper.toDto(saved);
    }

    @Override
    public Optional<CiudadResponseDTO> updateCiudad(long id, CiudadRequestDTO dto) {
        return ciudadRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    Ciudad updated = ciudadRepository.save(existing);
                    return ciudadMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteCiudad(long id) {
        if (!ciudadRepository.existsById(id)) return false;
        ciudadRepository.deleteById(id);
        return true;
    }
}
