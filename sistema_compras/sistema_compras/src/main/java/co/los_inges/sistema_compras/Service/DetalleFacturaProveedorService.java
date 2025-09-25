package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.DetalleFacturaProveedor;
import java.util.*;

public interface DetalleFacturaProveedorService {

    List<DetalleFacturaProveedor> getAllDetalleFacturaProveedores();

    Optional<DetalleFacturaProveedor> getDetalleFacturaProveedorById(long id);

    boolean deleteDetalleFacturaProveedor(long id);

    Optional<DetalleFacturaProveedor> updateDetalleFacturaProveedor(long id, DetalleFacturaProveedor detalleFacturaProveedor);

}
