package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.CategoriaService;
import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;
import co.los_inges.sistema_compras.mapping.CategoriaMapper;
import co.los_inges.sistema_compras.models.Categoria;
import co.los_inges.sistema_compras.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;


    @Override
    public List<CategoriaResponseDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.toDtoList(categorias);
    }

    @Override
    public Optional<CategoriaResponseDTO> getCategoriaById(long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDto);
    }

    @Override
    public CategoriaResponseDTO createCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria saved = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(saved);
    }

    @Override
    public Optional<CategoriaResponseDTO> updateCategoria(long id, CategoriaRequestDTO dto) {
        return categoriaRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    Categoria updated = categoriaRepository.save(existing);
                    return categoriaMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteCategoria(long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }
}

