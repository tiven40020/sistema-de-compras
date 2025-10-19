package co.los_inges.sistema_compras.service.Implementaciones;

import co.los_inges.sistema_compras.service.DetalleCompraService;
import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;
import co.los_inges.sistema_compras.mapping.DetalleCompraMapper;
import co.los_inges.sistema_compras.models.Compra;
import co.los_inges.sistema_compras.models.DetalleCompra;
import co.los_inges.sistema_compras.models.Producto;
import co.los_inges.sistema_compras.repositories.CompraRepository;
import co.los_inges.sistema_compras.repositories.DetalleCompraRepository;
import co.los_inges.sistema_compras.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleCompraServiceImpl implements DetalleCompraService {

    private final DetalleCompraRepository detalleCompraRepository;
    private final CompraRepository compraRepository;
    private final ProductoRepository productoRepository;
    private final DetalleCompraMapper detalleCompraMapper;

    @Override
    public List<DetalleCompraResponseDTO> getAllDetalles() {
        List<DetalleCompra> detalles = detalleCompraRepository.findAll();
        return detalleCompraMapper.toDtoList(detalles);
    }

    @Override
    public Optional<DetalleCompraResponseDTO> getDetalleById(long id) {
        return detalleCompraRepository.findById(id)
                .map(detalleCompraMapper::toDto);
    }

    @Override
    public DetalleCompraResponseDTO createDetalle(DetalleCompraRequestDTO dto) {
        DetalleCompra detalle = detalleCompraMapper.toEntity(dto);

        Compra compra = compraRepository.findById(dto.idCompra())
                .orElseThrow(() -> new IllegalArgumentException("Compra no encontrada"));

        Producto producto = productoRepository.findById(dto.idProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        detalle.setCompra(compra);
        detalle.setProducto(producto);

        producto.setStock(producto.getStock() + dto.cantidad());
        productoRepository.save(producto);

        DetalleCompra saved = detalleCompraRepository.save(detalle);
        return detalleCompraMapper.toDto(saved);
    }

    @Override
    public Optional<DetalleCompraResponseDTO> updateDetalle(long id, DetalleCompraRequestDTO dto) {
        return detalleCompraRepository.findById(id)
                .map(existing -> {
                    existing.setCantidad(dto.cantidad());

                    compraRepository.findById(dto.idCompra())
                            .ifPresent(existing::setCompra);

                    productoRepository.findById(dto.idProducto())
                            .ifPresent(existing::setProducto);

                    DetalleCompra updated = detalleCompraRepository.save(existing);
                    return detalleCompraMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteDetalle(long id) {
        if (!detalleCompraRepository.existsById(id)) return false;
        detalleCompraRepository.deleteById(id);
        return true;
    }
}
