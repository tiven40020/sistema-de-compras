package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import co.los_inges.sistema_compras.models.Proveedor;
import java.util.*;

public interface ProveedorService {

    List<ProveedorResponseDTO> getAllProveedores();

    Optional<ProveedorResponseDTO> getProveedorById(long id);

    ProveedorResponseDTO createProveedor(ProveedorRequestDTO proveedorRequestDTO);

    Optional<ProveedorResponseDTO> updateProveedor(long id, ProveedorRequestDTO proveedorRequestDTO);

    boolean deleteProveedor(long id);

}
