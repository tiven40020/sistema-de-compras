package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Categoria;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Override
    public List<Categoria> getAllCategorias() {
        return List.of();
    }

    @Override
    public Optional<Categoria> getCategoriaById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteCategoria(long id) {
        return false;
    }

    @Override
    public Optional<Categoria> updateCategoria(long id, Categoria categoria) {
        return Optional.empty();
    }
}
