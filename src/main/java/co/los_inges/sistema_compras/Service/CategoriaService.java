package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;
import co.los_inges.sistema_compras.models.Categoria;
import java.util.*;

public interface CategoriaService {


    List<CategoriaResponseDTO> getAllCategorias();

    Optional<CategoriaResponseDTO> getCategoriaById(long id);

    CategoriaResponseDTO createCategoria(CategoriaRequestDTO categoriaRequestDTO);

    Optional<CategoriaResponseDTO> updateCategoria(long id, CategoriaRequestDTO categoriaRequestDTO);

    boolean deleteCategoria(long id);
}
