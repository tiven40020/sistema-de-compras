package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProveedorTelefonoService {

    List<ProveedorTelefonoResponseDTO> getAllProveedorTelefonos();

    Optional<ProveedorTelefonoResponseDTO> getProveedorTelefonoById(long id);

    ProveedorTelefonoResponseDTO createProveedorTelefono(ProveedorTelefonoRequestDTO proveedorTelefonoRequestDTO);

    Optional<ProveedorTelefonoResponseDTO> updateProveedorTelefono(long id, ProveedorTelefonoRequestDTO proveedorTelefonoRequestDTO);

    boolean deleteProveedorTelefono(long id);
}
