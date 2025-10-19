package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.dtos.request.TelefonoRequestDTO;
import co.los_inges.sistema_compras.dtos.response.TelefonoResponseDTO;
import co.los_inges.sistema_compras.service.TelefonoService;
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
class TelefonoServiceTest {

    @Autowired
    private TelefonoService telefonoService;

    @Test
    void getAllTelefonos() {
        List<TelefonoResponseDTO> lista = telefonoService.getAllTelefonos();

        assertNotNull(lista, "La lista de teléfonos no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber al menos un teléfono cargado en el dataset");
    }

    @Test
    void getTelefonoById() {
        long idExistente = 1L;

        Optional<TelefonoResponseDTO> telefono = telefonoService.getTelefonoById(idExistente);

        assertTrue(telefono.isPresent(), "El teléfono con ID 1 no existe");
        assertNotNull(telefono.get().numero(), "El número de teléfono no debe ser nulo");
        assertTrue(telefono.get().numero().matches("^[0-9+\\-\\s]{2,15}$"),
                "El número de teléfono debe tener un formato válido");
    }

    @Test
    void createTelefono() {
        TelefonoRequestDTO nuevo = new TelefonoRequestDTO("+57 3101234567");

        TelefonoResponseDTO creado = telefonoService.createTelefono(nuevo);

        assertNotNull(creado, "El teléfono creado no debe ser nulo");
        assertNotNull(creado.idTelefono(), "Debe haberse generado un ID");
        assertEquals("+57 3101234567", creado.numero(), "El número de teléfono debe coincidir con el ingresado");
    }

    @Test
    void updateTelefono() {
        long idExistente = 1L;
        TelefonoRequestDTO update = new TelefonoRequestDTO("+57 3009876543");

        Optional<TelefonoResponseDTO> actualizado = telefonoService.updateTelefono(idExistente, update);

        assertTrue(actualizado.isPresent(), "El teléfono debería haberse actualizado");
        assertEquals("+57 3009876543", actualizado.get().numero(),
                "El número del teléfono no se actualizó correctamente");
    }

    @Test
    void deleteTelefono() {
        long idAEliminar = 2L;

        boolean eliminado = telefonoService.deleteTelefono(idAEliminar);

        assertTrue(eliminado, "El teléfono no se elimino correctamente");

        Optional<TelefonoResponseDTO> telefonoEliminado = telefonoService.getTelefonoById(idAEliminar);
        assertFalse(telefonoEliminado.isPresent(), "El teléfono aun existe luego de eliminarse");
    }
}