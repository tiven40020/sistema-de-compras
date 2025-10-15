package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ProductoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProductoResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Test
    void getAllProductos() {
        List<ProductoResponseDTO> productos = productoService.getAllProductos();

        assertNotNull(productos, "La lista de productos es nula");
        assertFalse(productos.isEmpty(), "La lista de productos esta vacia");
    }

    @Test
    void getProductoById() {
        Optional<ProductoResponseDTO> producto = productoService.getProductoById(1L);

        assertTrue(producto.isPresent(), "No se encontro un producto con ID 1");
        assertNotNull(producto.get().nombre(), "El nombre del producto es nulo");
    }

    @Test
    void createProducto() {

        ProductoRequestDTO nuevo = new ProductoRequestDTO(
                "Producto Test",
                1L, // idCategoria
                1L, // idMarca
                1L, // idUnidadMedida
                10, // cantidadUnidadesMedidas
                1L, // idImpuesto
                25000.0, // precio
                50, // stock
                true // estado
        );

        ProductoResponseDTO creado = productoService.createProducto(nuevo);

        assertNotNull(creado, "El producto creado es nulo");
        assertEquals("Producto Test", creado.nombre(), "El nombre del producto creado debe coincidir");
        assertTrue(creado.estado(), "El producto creado debe estar activo");
    }

    @Test
    void updateProducto() {
        ProductoRequestDTO actualizado = new ProductoRequestDTO(
                "Producto Actualizado",
                1L,
                1L,
                1L,
                15,
                1L,
                30000.0,
                80,
                true
        );

        Optional<ProductoResponseDTO> resultado = productoService.updateProducto(1L, actualizado);

        assertTrue(resultado.isPresent(), "El producto con ID 1 no se pudo actualizar");
        assertEquals("Producto Actualizado", resultado.get().nombre(), "El nombre del producto no se actualizó correctamente");
        assertEquals(30000.0, resultado.get().precio(), "El precio del producto no se actualizó correctamente");
    }

    @Test
    void deleteProducto() {
        boolean eliminado = productoService.deleteProducto(1L);
        assertTrue(eliminado, "No se elimino el producto con ID 1");
    }
}