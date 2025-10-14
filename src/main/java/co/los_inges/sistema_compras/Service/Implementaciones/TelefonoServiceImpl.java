package co.los_inges.sistema_compras.Service.Implementaciones;

import co.los_inges.sistema_compras.Service.TelefonoService;
import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;
import co.los_inges.sistema_compras.mapping.TelefonoMapper;
import co.los_inges.sistema_compras.models.Telefono;
import co.los_inges.sistema_compras.repositories.TelefonoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {


    private final TelefonoRepository telefonoRepository;
    private final TelefonoMapper telefonoMapper;

    @Override
    public List<TelefonoResponseDTO> getAllTelefonos() {
        List<Telefono> telefonos = telefonoRepository.findAll();
        return telefonoMapper.toDtoList(telefonos);
    }

    @Override
    public Optional<TelefonoResponseDTO> getTelefonoById(long id) {
        return telefonoRepository.findById(id)
                .map(telefonoMapper::toDto);
    }

    @Override
    public TelefonoResponseDTO createTelefono(TelefonoRequestDTO dto) {
        Telefono telefono = telefonoMapper.toEntity(dto);
        Telefono saved = telefonoRepository.save(telefono);
        return telefonoMapper.toDto(saved);
    }

    @Override
    public Optional<TelefonoResponseDTO> updateTelefono(long id, TelefonoRequestDTO dto) {
        return telefonoRepository.findById(id)
                .map(existing -> {
                    existing.setNumero(dto.numero());
                    Telefono updated = telefonoRepository.save(existing);
                    return telefonoMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteTelefono(long id) {
        if (!telefonoRepository.existsById(id)) return false;
        telefonoRepository.deleteById(id);
        return true;
    }
}
