package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ImpuestoService {

    List<ImpuestoResponseDTO> getAllImpuestos();

    Optional<ImpuestoResponseDTO> getImpuestoById(long id);

    ImpuestoResponseDTO createImpuesto(ImpuestoRequestDTO impuestoRequestDTO);

    Optional<ImpuestoResponseDTO> updateImpuesto(long id, ImpuestoRequestDTO impuestoRequestDTO);

    boolean deleteImpuesto(long id);
}
