package co.los_inges.sistema_compras.service;

import co.los_inges.sistema_compras.dtos.request.ProductoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProductoResponseDTO;

import java.util.*;

public interface ProductoService {

    List<ProductoResponseDTO> getAllProductos();

    Optional<ProductoResponseDTO> getProductoById(long id);

    ProductoResponseDTO createProducto(ProductoRequestDTO productoRequestDTO);

    Optional<ProductoResponseDTO> updateProducto(long id, ProductoRequestDTO productoRequestDTO);

    boolean deleteProducto(long id);

}
