package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CiudadService {

    List<CiudadResponseDTO> getAllCiudades();

    Optional<CiudadResponseDTO> getCiudadById(long id);

    CiudadResponseDTO createCiudad(CiudadRequestDTO ciudadRequestDTO);

    Optional<CiudadResponseDTO> updateCiudad(long id, CiudadRequestDTO ciudadRequestDTO);

    boolean deleteCiudad(long id);
}
