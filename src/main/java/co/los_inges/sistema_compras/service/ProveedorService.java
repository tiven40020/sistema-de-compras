package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;

import java.util.*;

public interface ProveedorService {

    List<ProveedorResponseDTO> getAllProveedores();

    Optional<ProveedorResponseDTO> getProveedorById(long id);

    ProveedorResponseDTO createProveedor(ProveedorRequestDTO proveedorRequestDTO);

    Optional<ProveedorResponseDTO> updateProveedor(long id, ProveedorRequestDTO proveedorRequestDTO);

    boolean deleteProveedor(long id);

}
