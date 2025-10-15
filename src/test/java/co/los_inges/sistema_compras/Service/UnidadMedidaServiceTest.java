package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.UnidadMedidaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.UnidadMedidaResponseDTO;
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

class UnidadMedidaServiceTest {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @Test
    void getAllUnidadesMedida() {
        List<UnidadMedidaResponseDTO> unidades = unidadMedidaService.getAllUnidadesMedida();

        assertNotNull(unidades);
        assertFalse(unidades.isEmpty(), "La lista de unidades no debería estar vacía");
    }

    @Test
    void getUnidadMedidaById() {
        Optional<UnidadMedidaResponseDTO> unidad = unidadMedidaService.getUnidadMedidaById(1L);

        assertTrue(unidad.isPresent(), "Debe existir una unidad de medida con ID 1 en el dataset");
        assertNotNull(unidad.get().nombre());
    }

    @Test
    void createUnidadMedida() {
        UnidadMedidaRequestDTO request = new UnidadMedidaRequestDTO("NuevaUnidad");

        UnidadMedidaResponseDTO creada = unidadMedidaService.createUnidadMedida(request);

        assertNotNull(creada);
        assertNotNull(creada.idUnidadMedida());
        assertEquals("NuevaUnidad", creada.nombre());
    }

    @Test
    void updateUnidadMedida() {
        UnidadMedidaRequestDTO request = new UnidadMedidaRequestDTO("Pulgadas");

        Optional<UnidadMedidaResponseDTO> actualizada =
                unidadMedidaService.updateUnidadMedida(1L, request);

        assertTrue(actualizada.isPresent(), "Debe poder actualizar la unidad con ID 1");
        assertEquals("Pulgadas", actualizada.get().nombre());
    }

    @Test
    void deleteUnidadMedida() {
        boolean eliminado = unidadMedidaService.deleteUnidadMedida(1L);

        assertTrue(eliminado, "No se elimino correctamente la unidad con ID 1");

        Optional<UnidadMedidaResponseDTO> buscada = unidadMedidaService.getUnidadMedidaById(1L);
        assertTrue(buscada.isEmpty(), "Después de eliminar, la unidad no debe existir");
    }
}