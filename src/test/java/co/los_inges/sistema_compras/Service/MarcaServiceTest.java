package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.MarcaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.MarcaResponseDTO;
import co.los_inges.sistema_compras.service.MarcaService;
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
class MarcaServiceTest {

    @Autowired
    private MarcaService marcaService;

    @Test
    void getAllMarcas() {
        List<MarcaResponseDTO> marcas = marcaService.getAllMarcas();
        assertNotNull(marcas);
        assertFalse(marcas.isEmpty(), "La lista de marcas esta vacia");
    }

    @Test
    void getMarcaById() {
        Optional<MarcaResponseDTO> marca = marcaService.getMarcaById(1L);
        assertTrue(marca.isPresent(), "No existe la marca con ID 1");
        assertNotNull(marca.get().nombre());
    }

    @Test
    void createMarca() {
        MarcaRequestDTO nueva = new MarcaRequestDTO("MarcaPrueba");
        MarcaResponseDTO creada = marcaService.createMarca(nueva);

        assertNotNull(creada);
        assertEquals("MarcaPrueba", creada.nombre());
    }

    @Test
    void updateMarca() {
        MarcaRequestDTO actualizada = new MarcaRequestDTO("MarcaActualizada");

        Optional<MarcaResponseDTO> resultado = marcaService.updateMarca(1L, actualizada);

        assertTrue(resultado.isPresent());
        assertEquals("MarcaActualizada", resultado.get().nombre());
    }

    @Test
    void deleteMarca() {
        boolean eliminada = marcaService.deleteMarca(1L);
        assertTrue(eliminada, "No se elimino la marca con ID 1");
    }
}