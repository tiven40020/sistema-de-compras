package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.ProveedorTelefonoService;
import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;
import co.los_inges.sistema_compras.mapping.ProveedorTelefonoMapper;
import co.los_inges.sistema_compras.models.Proveedor;
import co.los_inges.sistema_compras.models.ProveedorTelefono;
import co.los_inges.sistema_compras.models.Telefono;
import co.los_inges.sistema_compras.repositories.ProveedorRepository;
import co.los_inges.sistema_compras.repositories.ProveedorTelefonoRepository;
import co.los_inges.sistema_compras.repositories.TelefonoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorTelefonoServiceImpl implements ProveedorTelefonoService {


    private final ProveedorTelefonoRepository proveedorTelefonoRepository;
    private final ProveedorRepository proveedorRepository;
    private final TelefonoRepository telefonoRepository;
    private final ProveedorTelefonoMapper proveedorTelefonoMapper;

    @Override
    public List<ProveedorTelefonoResponseDTO> getAllProveedorTelefonos() {
        List<ProveedorTelefono> lista = proveedorTelefonoRepository.findAll();
        return proveedorTelefonoMapper.toDtoList(lista);
    }

    @Override
    public Optional<ProveedorTelefonoResponseDTO> getProveedorTelefonoById(long id) {
        return proveedorTelefonoRepository.findById(id)
                .map(proveedorTelefonoMapper::toDto);
    }

    @Override
    public ProveedorTelefonoResponseDTO createProveedorTelefono(ProveedorTelefonoRequestDTO dto) {
        Proveedor proveedor = proveedorRepository.findById(dto.idProveedor())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + dto.idProveedor()));

        Telefono telefono = telefonoRepository.findById(dto.idTelefono())
                .orElseThrow(() -> new IllegalArgumentException("Teléfono no encontrado con ID: " + dto.idTelefono()));

        ProveedorTelefono proveedorTelefono = proveedorTelefonoMapper.toEntity(dto);
        proveedorTelefono.setProveedor(proveedor);
        proveedorTelefono.setTelefono(telefono);

        ProveedorTelefono saved = proveedorTelefonoRepository.save(proveedorTelefono);
        return proveedorTelefonoMapper.toDto(saved);
    }

    @Override
    public Optional<ProveedorTelefonoResponseDTO> updateProveedorTelefono(long id, ProveedorTelefonoRequestDTO dto) {
        return proveedorTelefonoRepository.findById(id)
                .map(existing -> {
                    proveedorRepository.findById(dto.idProveedor()).ifPresent(existing::setProveedor);
                    telefonoRepository.findById(dto.idTelefono()).ifPresent(existing::setTelefono);
                    ProveedorTelefono updated = proveedorTelefonoRepository.save(existing);
                    return proveedorTelefonoMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteProveedorTelefono(long id) {
        if (!proveedorTelefonoRepository.existsById(id)) return false;
        proveedorTelefonoRepository.deleteById(id);
        return true;
    }

    @Override
    public List<String> obtenerTelefonosPorProveedor(Long idProveedor) {
        List<ProveedorTelefono> relaciones = proveedorTelefonoRepository.findByProveedorIdProveedor(idProveedor);

        return relaciones.stream()
                .map(rel -> rel.getTelefono().getNumero())
                .collect(Collectors.toList());
    }
}
