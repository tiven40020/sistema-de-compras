package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.DetalleFacturaProveedor;
import co.los_inges.sistema_compras.repositories.DetalleFacturaProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DetalleFacturaProveedorServiceImpl implements DetalleFacturaProveedorService{

    private final DetalleFacturaProveedorRepository detalleFacturaProveedorRepository;

    public DetalleFacturaProveedorServiceImpl (DetalleFacturaProveedorRepository detalleFacturaProveedorRepository){
        this.detalleFacturaProveedorRepository = detalleFacturaProveedorRepository;
    }
    @Override
    public List<DetalleFacturaProveedor> getAllDetalleFacturaProveedores()
    {
        return detalleFacturaProveedorRepository.findAll();
    }

    @Override
    public Optional<DetalleFacturaProveedor> getDetalleFacturaProveedorById(long id)
    {
        return detalleFacturaProveedorRepository.findById(id);
    }

    @Override
    public boolean deleteDetalleFacturaProveedor(long id)
    {
        if(detalleFacturaProveedorRepository.existsById(id)){
            detalleFacturaProveedorRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<DetalleFacturaProveedor> updateDetalleFacturaProveedor(long id, DetalleFacturaProveedor detalleFacturaProveedor) {
        Optional<DetalleFacturaProveedor> detalleExistenteOpt = detalleFacturaProveedorRepository.findById(id);

        if (detalleExistenteOpt.isPresent()) {
            DetalleFacturaProveedor detalleExistente = detalleExistenteOpt.get();

            if (detalleFacturaProveedor.getFacturaProveedor() != null) {
                detalleExistente.setFacturaProveedor(detalleFacturaProveedor.getFacturaProveedor());
            }
            if (detalleFacturaProveedor.getProducto() != null) {
                detalleExistente.setProducto(detalleFacturaProveedor.getProducto());
            }
            if (detalleFacturaProveedor.getCantidad() > 0) {
                detalleExistente.setCantidad(detalleFacturaProveedor.getCantidad());
            }
            if (detalleFacturaProveedor.getPrecio() > 0) {
                detalleExistente.setPrecio(detalleFacturaProveedor.getPrecio());
            }
            detalleFacturaProveedorRepository.save(detalleExistente);

            return Optional.of(detalleExistente);
        }
        return Optional.empty();
    }
}

