package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UnidadMedidaService {

    List<UnidadMedidaResponseDTO> getAllUnidadesMedida();

    Optional<UnidadMedidaResponseDTO> getUnidadMedidaById(long id);

    UnidadMedidaResponseDTO createUnidadMedida(UnidadMedidaRequestDTO unidadMedidaRequestDTO);

    Optional<UnidadMedidaResponseDTO> updateUnidadMedida(long id, UnidadMedidaRequestDTO unidadMedidaRequestDTO);

    boolean deleteUnidadMedida(long id);
}
