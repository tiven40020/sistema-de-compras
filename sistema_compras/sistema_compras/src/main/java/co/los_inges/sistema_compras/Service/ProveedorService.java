package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Proveedor;
import java.util.*;

public interface ProveedorService {

    List<Proveedor> getAllProveedores();

    Optional<Proveedor> getProveedorById(long id);

    boolean deleteProveedor(long id);

    Optional<Proveedor> updateProveedor(long id, Proveedor proveedor);
}
