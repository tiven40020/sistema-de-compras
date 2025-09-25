package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Producto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> getAllProductos() {
        return List.of();
    }

    @Override
    public Optional<Producto> getProductoById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteProducto(long id) {
        return false;
    }

    @Override
    public Optional<Producto> updateProducto(long id, Producto producto) {
        return Optional.empty();
    }
}
