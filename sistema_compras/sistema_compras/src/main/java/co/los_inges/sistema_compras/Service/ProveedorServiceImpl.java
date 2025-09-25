package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Proveedor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    @Override
    public List<Proveedor> getAllProveedores() {
        return List.of();
    }

    @Override
    public Optional<Proveedor> getProveedorById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteProveedor(long id) {
        return false;
    }

    @Override
    public Optional<Proveedor> updateProveedor(long id, Proveedor proveedor) {
        return Optional.empty();
    }
}
