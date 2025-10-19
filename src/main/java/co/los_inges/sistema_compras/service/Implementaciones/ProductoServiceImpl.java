package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.ProductoService;
import co.los_inges.sistema_compras.dtos.request.ProductoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProductoResponseDTO;
import co.los_inges.sistema_compras.mapping.ProductoMapper;
import co.los_inges.sistema_compras.models.*;
import co.los_inges.sistema_compras.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ImpuestoRepository impuestoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoResponseDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toDtoList(productos);
    }

    @Override
    public Optional<ProductoResponseDTO> getProductoById(long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto);
    }

    @Override
    public ProductoResponseDTO createProducto(ProductoRequestDTO dto) {
        Producto producto = productoMapper.toEntity(dto);

        Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        Marca marca = marcaRepository.findById(dto.idMarca())
                .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada"));
        UnidadMedida unidad = unidadMedidaRepository.findById(dto.idUnidadMedida())
                .orElseThrow(() -> new IllegalArgumentException("Unidad de medida no encontrada"));
        Impuesto impuesto = impuestoRepository.findById(dto.idImpuesto())
                .orElseThrow(() -> new IllegalArgumentException("Impuesto no encontrado"));

        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setUnidadMedida(unidad);
        producto.setImpuesto(impuesto);

        Producto saved = productoRepository.save(producto);
        return productoMapper.toDto(saved);
    }

    @Override
    public Optional<ProductoResponseDTO> updateProducto(long id, ProductoRequestDTO dto) {
        return productoRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(dto.nombre());
                    existing.setPrecio(dto.precio());
                    existing.setStock(dto.stock());
                    existing.setEstado(dto.estado());
                    existing.setCantidadUnidadesMedidas(dto.cantidadUnidadesMedidas());

                    categoriaRepository.findById(dto.idCategoria()).ifPresent(existing::setCategoria);
                    marcaRepository.findById(dto.idMarca()).ifPresent(existing::setMarca);
                    unidadMedidaRepository.findById(dto.idUnidadMedida()).ifPresent(existing::setUnidadMedida);
                    impuestoRepository.findById(dto.idImpuesto()).ifPresent(existing::setImpuesto);

                    Producto updated = productoRepository.save(existing);
                    return productoMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteProducto(long id) {
        if (!productoRepository.existsById(id)) return false;
        productoRepository.deleteById(id);
        return true;
    }
}