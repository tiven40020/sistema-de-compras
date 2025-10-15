package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.CiudadRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CiudadResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@ActiveProfiles("test") // usa el dataset de prueba
@Transactional
class CiudadServiceTest {

    @Autowired
    private CiudadService ciudadService;

    @Test
    void getAllCiudades() {
        List<CiudadResponseDTO> ciudades = ciudadService.getAllCiudades();

        Assertions.assertNotNull(ciudades);
        Assertions.assertFalse(ciudades.isEmpty(), "La lista de ciudades no debería estar vacía");
        assertTrue(ciudades.stream().anyMatch(c -> c.nombre().equalsIgnoreCase("Medellin")));
    }

    @Test
    void getCiudadById() {
        Optional<CiudadResponseDTO> ciudad = ciudadService.getCiudadById(1L);

        assertTrue(ciudad.isPresent(), "La ciudad con ID 1 debería existir en el dataset");
        Assertions.assertEquals("Medellin", ciudad.get().nombre());
    }

    @Test
    void createCiudad() {
        CiudadRequestDTO nuevaCiudad = new CiudadRequestDTO("Cartagena");

        CiudadResponseDTO creada = ciudadService.createCiudad(nuevaCiudad);

        Assertions.assertNotNull(creada);
        Assertions.assertNotNull(creada.idCiudad());
        Assertions.assertEquals("Cartagena", creada.nombre());
    }

    @Test
    void updateCiudad() {
        CiudadRequestDTO actualizada = new CiudadRequestDTO("Medellin Actualizada");

        Optional<CiudadResponseDTO> resultado = ciudadService.updateCiudad(1L, actualizada);

        assertTrue(resultado.isPresent(), "La ciudad a actualizar debería existir");
        Assertions.assertEquals("Medellin Actualizada", resultado.get().nombre());
    }

    @Test
    void deleteCiudad() {
        boolean eliminado = ciudadService.deleteCiudad(2L);

        assertTrue(eliminado, "La ciudad con ID 2 debería eliminarse correctamente");
        Assertions.assertFalse(ciudadService.getCiudadById(2L).isPresent(), "La ciudad eliminada no debería encontrarse");
    }
}