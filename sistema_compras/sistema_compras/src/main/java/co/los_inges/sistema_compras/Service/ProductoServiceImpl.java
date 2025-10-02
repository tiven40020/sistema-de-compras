package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Producto;
import co.los_inges.sistema_compras.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl (ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    @Override
    public List<Producto> getAllProductos()
    {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(long id)
    {
        return productoRepository.findById(id);
    }

    @Override
    public boolean deleteProducto(long id)
    {
        if(productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Producto> updateProducto(long id, Producto producto)
    {
        Optional<Producto> productoExitente = productoRepository.findById(id);

        if(productoExitente.isPresent()){
            Producto aux = productoExitente.get();
            if(producto.getCategoria() != null) {
                aux.setCategoria(producto.getCategoria());
            }
            if(producto.getNombre() != null && !producto.getNombre().isBlank()) {
                aux.setNombre(producto.getNombre());
            }
            if(producto.getDescripcion() != null && !producto.getDescripcion().isBlank()){
                aux.setDescripcion(producto.getDescripcion());
            }
            if(producto.getStock() >=0){
                aux.setStock(producto.getStock());
            }
            if(producto.getPrecio() >=0){
                aux.setPrecio(producto.getPrecio());
            }
            if(producto.getStockMinimo() >=0){
                aux.setStockMinimo(producto.getStockMinimo());
            }
            productoRepository.save(aux);
            return Optional.of(aux);
        }
        return Optional.empty();
    }
}