package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.DetalleFacturaProveedor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DetalleFacturaProveedorServiceImpl implements DetalleFacturaProveedorService{

    @Override
    public List<DetalleFacturaProveedor> getAllDetalleFacturaProveedores() {
        return List.of();
    }

    @Override
    public Optional<DetalleFacturaProveedor> getDetalleFacturaProveedorById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDetalleFacturaProveedor(long id) {
        return false;
    }

    @Override
    public Optional<DetalleFacturaProveedor> updateDetalleFacturaProveedor(long id, DetalleFacturaProveedor detalleFacturaProveedor) {
        return Optional.empty();
    }
}
