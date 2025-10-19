package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.DetalleCompraResponseDTO;
import co.los_inges.sistema_compras.service.DetalleCompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DetalleCompraServiceTest {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @Test
    void getAllDetalles() {
        List<DetalleCompraResponseDTO> detalles = detalleCompraService.getAllDetalles();

        assertNotNull(detalles, "La lista de detalles no debe ser nula");
        assertFalse(detalles.isEmpty(), "No se encontraron detalles de compra en el dataset");
        assertNotNull(detalles.get(0).idDetalleCompra(), "El detalle debe tener un ID válido");
    }

    @Test
    void getDetalleById() {
        Optional<DetalleCompraResponseDTO> detalle = detalleCompraService.getDetalleById(1L);

        assertTrue(detalle.isPresent(), "El detalle con ID 1 debería existir");
        assertTrue(detalle.get().cantidad() > 0, "La cantidad debe ser mayor que 0");
        assertNotNull(detalle.get().producto(), "Debe tener un producto asociado");
        assertNotNull(detalle.get().idDetalleCompra(), "Debe tener una compra asociada");
    }

    @Test
    void createDetalle() {

        Long idCompra = 1L;
        Long idProducto = 1L;

        DetalleCompraRequestDTO nuevoDetalle = new DetalleCompraRequestDTO(
                idCompra,
                idProducto,
                7
        );

        DetalleCompraResponseDTO creado = detalleCompraService.createDetalle(nuevoDetalle);

        assertNotNull(creado, "El detalle creado no debe ser nulo");
        assertNotNull(creado.idDetalleCompra(), "Debe generarse un ID para el nuevo detalle");
        assertEquals(7, creado.cantidad(), "La cantidad del detalle debe coincidir con la enviada");
    }

    @Test
    void updateDetalle() {
        Long idExistente = 1L;

        DetalleCompraRequestDTO actualizado = new DetalleCompraRequestDTO(
                1L, // idCompra
                1L, // idProducto
                10  // nueva cantidad
        );

        Optional<DetalleCompraResponseDTO> resultado = detalleCompraService.updateDetalle(idExistente, actualizado);

        assertTrue(resultado.isPresent(), "El detalle con ID 1 debería existir");
        assertEquals(10, resultado.get().cantidad(), "La cantidad debe haberse actualizado correctamente");
    }

    @Test
    void deleteDetalle() {
        Long idEliminar = 2L;

        boolean eliminado = detalleCompraService.deleteDetalle(idEliminar);

        assertTrue(eliminado, "El detalle con ID 2 debería eliminarse correctamente");
        assertFalse(detalleCompraService.getDetalleById(idEliminar).isPresent(), "El detalle eliminado no debería encontrarse");
    }
}