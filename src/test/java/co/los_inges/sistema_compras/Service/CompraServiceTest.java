package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.CompraRequestDTO;
import co.los_inges.sistema_compras.dtos.request.DetalleCompraRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CompraResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CompraServiceTest {

    @Autowired
    private CompraService compraService;

    @Test
    void getAllCompras() {
        List<CompraResponseDTO> compras = compraService.getAllCompras();

        assertNotNull(compras, "La lista de compras no debe ser nula");
        assertFalse(compras.isEmpty(), "Debe haber al menos una compra en el dataset");
        assertNotNull(compras.get(0).idCompra(), "Cada compra debe tener un ID válido");
    }

    @Test
    void getCompraById() {
        Optional<CompraResponseDTO> compra = compraService.getCompraById(1L);

        assertTrue(compra.isPresent(), "La compra con ID 1 debería existir");
        assertNotNull(compra.get().proveedor(), "La compra debe tener un proveedor asociado");
        assertNotNull(compra.get().usuario(), "La compra debe tener un usuario asociado");
    }

    @Test
    void createCompra() {

        Long idProveedor = 1L;
        Long idUsuario = 1L;
        Long idProducto = 1L;

        DetalleCompraRequestDTO detalle = new DetalleCompraRequestDTO(
                0L,
                idProducto,
                5
        );

        CompraRequestDTO nuevaCompra = new CompraRequestDTO(
                new Date(2025-2-21),
                999,
                idProveedor,
                idUsuario,
                List.of(detalle)
        );

        CompraResponseDTO creada = compraService.createCompra(nuevaCompra);

        assertNotNull(creada, "La compra creada no debe ser nula");
        assertNotNull(creada.idCompra(), "Debe generarse un ID para la compra");
        assertEquals(999, creada.numeroFactura(), "El número de factura debe coincidir");
    }

    @Test
    void updateCompra() {
        CompraRequestDTO actualizada = new CompraRequestDTO(
                new Date(2025-6-4),
                1234,
                1L,
                1L,
                List.of()
        );

        Optional<CompraResponseDTO> resultado = compraService.updateCompra(1L, actualizada);

        assertTrue(resultado.isPresent(), "La compra con ID 1 debería existir");
        assertEquals(1234, resultado.get().numeroFactura(), "El número de factura debe actualizarse correctamente");
    }

    @Test
    void deleteCompra() {
        boolean eliminado = compraService.deleteCompra(2L);

        assertTrue(eliminado, "La compra con ID 2 debería eliminarse correctamente");
        assertFalse(compraService.getCompraById(2L).isPresent(), "La compra eliminada no debería encontrarse");
    }
}