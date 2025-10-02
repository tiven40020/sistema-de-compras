package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Categoria;
import co.los_inges.sistema_compras.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl (CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> getAllCategorias()
    {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getCategoriaById(long id)
    {
        return categoriaRepository.findById(id);
    }

    @Override
    public boolean deleteCategoria(long id) {

        if(categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Categoria> updateCategoria(long id, Categoria categoria) {

        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if(categoriaExistente.isPresent()){
            Categoria aux = categoriaExistente.get();
            if(categoria.getNombre() != null && !categoria.getNombre().isBlank()) {
                aux.setNombre(categoria.getNombre());
            }
            categoriaRepository.save(aux);
            return Optional.of(aux);
        }
        return Optional.empty();
    }
}

