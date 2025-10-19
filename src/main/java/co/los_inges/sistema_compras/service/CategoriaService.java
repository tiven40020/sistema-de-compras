package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;

import java.util.*;

public interface CategoriaService {


    List<CategoriaResponseDTO> getAllCategorias();

    Optional<CategoriaResponseDTO> getCategoriaById(long id);

    CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO);

    Optional<CategoriaResponseDTO> updateCategoria(long id, CategoriaRequestDTO categoriaRequestDTO);

    boolean deleteCategoria(long id);
}
