package co.los_inges.sistema_compras.Service.Implementaciones;

import co.los_inges.sistema_compras.Service.CompraService;
import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;
import co.los_inges.sistema_compras.mapping.CompraMapper;
import co.los_inges.sistema_compras.mapping.DetalleCompraMapper;
import co.los_inges.sistema_compras.models.*;
import co.los_inges.sistema_compras.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {


    private final CompraRepository compraRepository;
    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetalleCompraRepository detalleCompraRepository;
    private final ProductoRepository productoRepository;
    private final CompraMapper compraMapper;
    private final DetalleCompraMapper detalleCompraMapper;

    @Override
    public List<CompraResponseDTO> getAllCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compraMapper.toDtoList(compras);
    }

    @Override
    public Optional<CompraResponseDTO> getCompraById(long id) {
        return compraRepository.findById(id)
                .map(compraMapper::toDto);
    }

    @Override
    public CompraResponseDTO createCompra(CompraRequestDTO dto) {
        Compra compra = compraMapper.toEntity(dto);

        Proveedor proveedor = proveedorRepository.findById(dto.idProveedor())
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        compra.setProveedor(proveedor);
        compra.setUsuario(usuario);

        Compra savedCompra = compraRepository.save(compra);

        if (dto.detallesCompra() != null && !dto.detallesCompra().isEmpty()) {
            for (DetalleCompraRequestDTO detalleDTO : dto.detallesCompra()) {

                Producto producto = productoRepository.findById(detalleDTO.idProducto())
                        .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

                DetalleCompra detalle = detalleCompraMapper.toEntity(detalleDTO);
                detalle.setCompra(savedCompra);
                detalle.setProducto(producto);

                detalleCompraRepository.save(detalle);

                producto.setStock(producto.getStock() + detalle.getCantidad());
                productoRepository.save(producto);
            }
        }

        return compraMapper.toDto(savedCompra);
    }

    @Override
    public Optional<CompraResponseDTO> updateCompra(long id, CompraRequestDTO dto) {
        return compraRepository.findById(id)
                .map(existing -> {
                    existing.setFecha(dto.fecha());
                    existing.setNumeroFactura(dto.numeroFactura());

                    proveedorRepository.findById(dto.idProveedor()).ifPresent(existing::setProveedor);
                    usuarioRepository.findById(dto.idUsuario()).ifPresent(existing::setUsuario);

                    Compra updated = compraRepository.save(existing);
                    return compraMapper.toDto(updated);
                });
    }

    @Override
    public boolean deleteCompra(long id) {
        if (!compraRepository.existsById(id)) return false;
        compraRepository.deleteById(id);
        return true;
    }
}
