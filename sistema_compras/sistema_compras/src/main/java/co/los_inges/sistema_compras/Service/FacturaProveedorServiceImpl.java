package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.FacturaProveedor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacturaProveedorServiceImpl implements FacturaProveedorService{

    @Override
    public List<FacturaProveedor> getAllFacturasProveedores() {
        return List.of();
    }

    @Override
    public Optional<FacturaProveedor> getFacturaProveedorById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteFacturaProveedor(long id) {
        return false;
    }

    @Override
    public Optional<FacturaProveedor> updateFacturaProveedor(long id, FacturaProveedor facturaProveedor) {
        return Optional.empty();
    }
}
