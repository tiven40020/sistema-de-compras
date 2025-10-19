package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CompraService {

    List<CompraResponseDTO> getAllCompras();

    Optional<CompraResponseDTO> getCompraById(long id);

    CompraResponseDTO createCompra(CompraRequestDTO compraRequestDTO);

    Optional<CompraResponseDTO> updateCompra(long id, CompraRequestDTO compraRequestDTO);

    boolean deleteCompra(long id);
}
