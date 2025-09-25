package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.FacturaProveedor;
import java.util.*;

public interface FacturaProveedorService {

    List<FacturaProveedor> getAllFacturasProveedores();

    Optional<FacturaProveedor> getFacturaProveedorById(long id);

    boolean deleteFacturaProveedor (long id);

    Optional<FacturaProveedor> updateFacturaProveedor(long id, FacturaProveedor facturaProveedor);
}
