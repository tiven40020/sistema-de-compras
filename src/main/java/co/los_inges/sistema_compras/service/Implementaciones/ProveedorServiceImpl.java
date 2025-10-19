package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.ProveedorService;
import co.los_inges.sistema_compras.service.ProveedorTelefonoService;
import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import co.los_inges.sistema_compras.mapping.CiudadMapper;
import co.los_inges.sistema_compras.mapping.ProveedorMapper;
import co.los_inges.sistema_compras.models.Ciudad;
import co.los_inges.sistema_compras.models.Proveedor;
import co.los_inges.sistema_compras.models.ProveedorTelefono;
import co.los_inges.sistema_compras.models.Telefono;
import co.los_inges.sistema_compras.repositories.CiudadRepository;
import co.los_inges.sistema_compras.repositories.ProveedorRepository;
import co.los_inges.sistema_compras.repositories.ProveedorTelefonoRepository;
import co.los_inges.sistema_compras.repositories.TelefonoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService{


    private final ProveedorRepository proveedorRepository;
    private final CiudadRepository ciudadRepository;
    private final TelefonoRepository telefonoRepository;
    private final ProveedorTelefonoRepository proveedorTelefonoRepository;
    private final ProveedorMapper proveedorMapper;
    private final CiudadMapper ciudadMapper;
    private final ProveedorTelefonoService proveedorTelefonoService;

    @Override
    public List<ProveedorResponseDTO> getAllProveedores() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedorMapper.toDtoList(proveedores);
    }

    @Override
    public Optional<ProveedorResponseDTO> getProveedorById(long id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    ProveedorResponseDTO dtoBase = proveedorMapper.toDto(proveedor);

                    // Obtenemos los teléfonos asociados al proveedor
                    List<String> telefonos = proveedorTelefonoService.obtenerTelefonosPorProveedor(id);

                    // Retornamos un nuevo DTO con la lista de teléfonos actualizada
                    return new ProveedorResponseDTO(
                            dtoBase.idProveedor(),
                            dtoBase.nombre(),
                            dtoBase.ciudad(),
                            dtoBase.direccion(),
                            dtoBase.email(),
                            dtoBase.estado(),
                            telefonos
                    );
                });
    }


    @Override
    public ProveedorResponseDTO createProveedor(ProveedorRequestDTO dto) {

        Proveedor proveedor = proveedorMapper.toEntity(dto);

        Ciudad ciudad = ciudadRepository.findById(dto.idCiudad())
                .orElseThrow(() -> new IllegalArgumentException("Ciudad no encontrada"));

        proveedor.setCiudad(ciudad);

        Proveedor savedProveedor = proveedorRepository.save(proveedor);

        if (dto.telefonos() != null && !dto.telefonos().isEmpty()) {
            for (String numero : dto.telefonos()) {
                Telefono telefono = telefonoRepository.save(
                        Telefono.builder().numero(numero).build()
                );
                ProveedorTelefono rel = ProveedorTelefono.builder()
                        .proveedor(savedProveedor)
                        .telefono(telefono)
                        .build();
                proveedorTelefonoRepository.save(rel);
            }
        }
        List<String> telefonos = proveedorTelefonoService.obtenerTelefonosPorProveedor(savedProveedor.getIdProveedor());

        // Retornamos un nuevo DTO con la lista de teléfonos actualizada
        return new ProveedorResponseDTO(
                savedProveedor.getIdProveedor(),
                savedProveedor.getNombre(),
                ciudadMapper.toDto(savedProveedor.getCiudad()),
                savedProveedor.getDireccion(),
                savedProveedor.getEmail(),
                savedProveedor.isEstado(),
                telefonos
        );
    }

    @Override
    public Optional<ProveedorResponseDTO> updateProveedor(long id, ProveedorRequestDTO dto) {
        return proveedorRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    existing.setDireccion(dto.direccion());
                    existing.setEmail(dto.email());
                    existing.setEstado(dto.estado());

                    ciudadRepository.findById(dto.idCiudad())
                            .ifPresent(existing::setCiudad);

                    Proveedor updated = proveedorRepository.save(existing);
                    return proveedorMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteProveedor(long id) {
        if (!proveedorRepository.existsById(id)) return false;
        proveedorRepository.deleteById(id);
        return true;
    }
}
