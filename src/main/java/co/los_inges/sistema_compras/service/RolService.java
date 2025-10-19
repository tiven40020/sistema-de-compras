package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.RolRequestDTO;
import co.los_inges.sistema_compras.dtos.response.RolResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<RolResponseDTO> getAllRoles();

    Optional<RolResponseDTO> getRolById(long id);

    RolResponseDTO createRol(RolRequestDTO rolRequestDTO);

    Optional<RolResponseDTO> updateRol(long id, RolRequestDTO rolRequestDTO);

    boolean deleteRol(long id);

}
