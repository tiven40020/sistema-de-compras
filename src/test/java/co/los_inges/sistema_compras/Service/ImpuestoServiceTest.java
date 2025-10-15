package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.ImpuestoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.ImpuestoResponseDTO;
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
class ImpuestoServiceTest {

    @Autowired
    private ImpuestoService impuestoService;

    @Test
    void getAllImpuestos() {
        List<ImpuestoResponseDTO> impuestos = impuestoService.getAllImpuestos();

        assertNotNull(impuestos);
        assertFalse(impuestos.isEmpty(), "La lista de impuestos esta vacía.");
    }

    @Test
    void getImpuestoById() {
        Optional<ImpuestoResponseDTO> impuesto = impuestoService.getImpuestoById(1L);

        assertTrue(impuesto.isPresent(), "No se encontro el impuesto.");
        assertNotNull(impuesto.get().nombre());
        assertEquals(19.0,impuesto.get().porcentaje());
    }

    @Test
    void createImpuesto() {
        ImpuestoRequestDTO nuevo = new ImpuestoRequestDTO("Retefuente", 10.0);

        ImpuestoResponseDTO creado = impuestoService.createImpuesto(nuevo);

        assertNotNull(creado);
        assertEquals("Retefuente", creado.nombre());
        assertEquals(10.0, creado.porcentaje());
    }

    @Test
    void updateImpuesto() {
        ImpuestoRequestDTO actualizado = new ImpuestoRequestDTO("IVA actualizado", 18.0);

        Optional<ImpuestoResponseDTO> result = impuestoService.updateImpuesto(1L, actualizado);

        assertTrue(result.isPresent());
        assertEquals("IVA actualizado", result.get().nombre());
        assertEquals(18.0, result.get().porcentaje());
    }

    @Test
    void deleteImpuesto() {
        boolean eliminado = impuestoService.deleteImpuesto(1L);

        assertTrue(eliminado, "Debe poder eliminar el impuesto con ID 1.");

        Optional<ImpuestoResponseDTO> deleted = impuestoService.getImpuestoById(1L);
        assertTrue(deleted.isEmpty(), "El impuesto eliminado no debe existir más.");
    }
}