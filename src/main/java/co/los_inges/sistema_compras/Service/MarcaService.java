package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
import java.util.List;
import java.util.Optional;

public interface MarcaService {

    List<MarcaResponseDTO> getAllMarcas();

    Optional<MarcaResponseDTO> getMarcaById(long id);

    MarcaResponseDTO createMarca(MarcaRequestDTO marcaRequestDTO);

    Optional<MarcaResponseDTO> updateMarca(long id, MarcaRequestDTO marcaRequestDTO);

    boolean deleteMarca(long id);
}
