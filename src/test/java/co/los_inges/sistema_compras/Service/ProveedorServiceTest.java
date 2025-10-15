package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ProveedorRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProveedorServiceTest {

    @Autowired
    private ProveedorService proveedorService;

    @Test
    void getAllProveedores() {
        List<ProveedorResponseDTO> proveedores = proveedorService.getAllProveedores();

        assertNotNull(proveedores);
        assertFalse(proveedores.isEmpty(), "La lista de proveedores esta vacía");
    }

    @Test
    void getProveedorById() {
        long idExistente = 1L;

        Optional<ProveedorResponseDTO> proveedor = proveedorService.getProveedorById(idExistente);

        assertTrue(proveedor.isPresent(), "El proveedor con ID 1 no existir");
        assertNotNull(proveedor.get().nombre(), "El nombre del proveedor no debe ser nulo");
        assertNotNull(proveedor.get().ciudad(), "El proveedor debe tener una ciudad asociada");
        assertEquals("Proveedor A", proveedor.get().nombre());
    }

    @Test
    void createProveedor() {
        ProveedorRequestDTO nuevoProveedor = new ProveedorRequestDTO(
                "Proveedor Nuevo Test",
                1L,
                "Calle 123",
                "proveedor@nuevotest.com",
                true,
                List.of("3009876543")
        );

        ProveedorResponseDTO creado = proveedorService.createProveedor(nuevoProveedor);

        assertNotNull(creado);
        assertNotNull(creado.idProveedor());
        assertEquals("Proveedor Nuevo Test", creado.nombre());
        assertEquals("Calle 123", creado.direccion());
        assertTrue(creado.estado());
    }

    @Test
    void updateProveedor() {
        long idProveedorExistente = 1L;

        ProveedorRequestDTO update = new ProveedorRequestDTO(
                "Proveedor Actualizado",
                1L,
                "Nueva Dirección 532",
                "actualizado@test.com",
                true,
                List.of("3011112233")
        );

        Optional<ProveedorResponseDTO> actualizado = proveedorService.updateProveedor(idProveedorExistente, update);

        assertTrue(actualizado.isPresent(), "El proveedor debería haberse actualizado");
        assertEquals("Proveedor Actualizado", actualizado.get().nombre());
        assertEquals("Nueva Dirección 532", actualizado.get().direccion());
    }

    @Test
    void deleteProveedor() {
        long idProveedorAEliminar = 2L;

        boolean eliminado = proveedorService.deleteProveedor(idProveedorAEliminar);

        assertTrue(eliminado, "El proveedor no se elimino correctamente");

        Optional<ProveedorResponseDTO> eliminadoCheck = proveedorService.getProveedorById(idProveedorAEliminar);
        assertFalse(eliminadoCheck.isPresent(), "El proveedor aun existe");
    }
}