package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TelefonoService {

    List<TelefonoResponseDTO> getAllTelefonos();

    Optional<TelefonoResponseDTO> getTelefonoById(long id);

    TelefonoResponseDTO createTelefono(TelefonoRequestDTO telefonoRequestDTO);

    Optional<TelefonoResponseDTO> updateTelefono(long id, TelefonoRequestDTO telefonoRequestDTO);

    boolean deleteTelefono(long id);
}
