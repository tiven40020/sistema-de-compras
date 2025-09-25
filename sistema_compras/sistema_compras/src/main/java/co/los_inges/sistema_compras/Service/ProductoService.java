package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Producto;
import java.util.*;

public interface ProductoService {

    List<Producto> getAllProductos();

    Optional<Producto> getProductoById(long id);

    boolean deleteProducto(long id);

    Optional<Producto> updateProducto(long id, Producto producto);

}
