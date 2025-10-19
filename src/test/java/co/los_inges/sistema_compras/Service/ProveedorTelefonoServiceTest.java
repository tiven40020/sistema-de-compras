package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ProveedorTelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ProveedorTelefonoResponseDTO;
import co.los_inges.sistema_compras.service.ProveedorTelefonoService;
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
class ProveedorTelefonoServiceTest {

    @Autowired
    private ProveedorTelefonoService proveedorTelefonoService;

    @Test
    void getAllProveedorTelefonos() {
        List<ProveedorTelefonoResponseDTO> lista = proveedorTelefonoService.getAllProveedorTelefonos();

        assertNotNull(lista);
        assertFalse(lista.isEmpty(), "La lista de relaciones proveedor-teléfono esta vacía");
    }

    @Test
    void getProveedorTelefonoById() {
        long idExistente = 1L;

        Optional<ProveedorTelefonoResponseDTO> relacion = proveedorTelefonoService.getProveedorTelefonoById(idExistente);

        assertTrue(relacion.isPresent(), "La relación con ID 1 no existe");
        assertNotNull(relacion.get().proveedor(), "Debe tener un proveedor asociado");
        assertNotNull(relacion.get().telefono(), "Debe tener un teléfono asociado");
    }

    @Test
    void createProveedorTelefono() {

        ProveedorTelefonoRequestDTO nuevaRelacion = new ProveedorTelefonoRequestDTO(
                1L,
                2L
        );

        ProveedorTelefonoResponseDTO creada = proveedorTelefonoService.createProveedorTelefono(nuevaRelacion);

        assertNotNull(creada);
        assertNotNull(creada.idProveedoresTelefonos());
        assertEquals(1L, creada.proveedor().idProveedor());
        assertEquals(2L, creada.telefono().idTelefono());
    }

    @Test
    void updateProveedorTelefono() {
        long idRelacionExistente = 1L;

        ProveedorTelefonoRequestDTO update = new ProveedorTelefonoRequestDTO(
                1L,
                3L
        );

        Optional<ProveedorTelefonoResponseDTO> actualizada = proveedorTelefonoService.updateProveedorTelefono(idRelacionExistente, update);

        assertTrue(actualizada.isPresent(), "La relación no se actualizo");
        assertEquals(3L, actualizada.get().telefono().idTelefono(), "El teléfono asociado no se actualizo");
    }

    @Test
    void deleteProveedorTelefono() {
        long idAEliminar = 2L;

        boolean eliminado = proveedorTelefonoService.deleteProveedorTelefono(idAEliminar);

        assertTrue(eliminado, "La relación proveedor-teléfono no se elimino correctamente");

        Optional<ProveedorTelefonoResponseDTO> eliminadoCheck = proveedorTelefonoService.getProveedorTelefonoById(idAEliminar);
        assertFalse(eliminadoCheck.isPresent(), "La relación aun existe después de eliminarse");
    }

    @Test
    void obtenerTelefonosPorProveedor() {
        long idProveedor = 1L;

        List<String> telefonos = proveedorTelefonoService.obtenerTelefonosPorProveedor(idProveedor);

        assertNotNull(telefonos);
        assertFalse(telefonos.isEmpty(), "El proveedor debería tener al menos un teléfono asociado");
        telefonos.forEach(t -> assertTrue(t.matches("^[0-9+\\-\\s]{2,15}$"), "El número de teléfono tiene un formato inválido"));
    }
}