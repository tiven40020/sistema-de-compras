package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;

import java.util.List;
import java.util.Optional;

public interface DetalleCompraService {

    List<DetalleCompraResponseDTO> getAllDetalles();

    Optional<DetalleCompraResponseDTO> getDetalleById(long id);

    DetalleCompraResponseDTO createDetalle(DetalleCompraRequestDTO detalleCompraRequestDTO);

    Optional<DetalleCompraResponseDTO> updateDetalle(long id, DetalleCompraRequestDTO detalleCompraRequestDTO);

    boolean deleteDetalle(long id);
}
