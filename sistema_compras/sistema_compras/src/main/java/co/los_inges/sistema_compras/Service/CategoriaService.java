package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Categoria;
import java.util.*;

public interface CategoriaService {

    List<Categoria> getAllCategorias();

    Optional<Categoria> getCategoriaById(long id);

    boolean deleteCategoria(long id);

    Optional<Categoria> updateCategoria(long id, Categoria categoria);
}
