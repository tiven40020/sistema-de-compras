package co.los_inges.sistema_compras.Service;


import co.los_inges.sistema_compras.dtos.request.CategoriaRequestDTO;
import co.los_inges.sistema_compras.dtos.response.CategoriaResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void getAllCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.getAllCategorias();

        Assertions.assertNotNull(categorias);
        Assertions.assertFalse(categorias.isEmpty());
        assertTrue(categorias.size() >= 3);
    }

    @Test
    void getCategoriaById() {
        long idExistente = 1L;
        Optional<CategoriaResponseDTO> categoria = categoriaService.getCategoriaById(idExistente);

        assertTrue(categoria.isPresent());
        Assertions.assertEquals(idExistente, categoria.get().idCategoria());
    }

    @Test
    void createCategoria() {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Nueva Categoria Test");

        CategoriaResponseDTO creada = categoriaService.createCategoria(request);

        Assertions.assertNotNull(creada);
        Assertions.assertNotNull(creada.idCategoria());
        Assertions.assertEquals("Nueva Categoria Test", creada.nombre());
    }

    @Test
    void updateCategoria() {
        long idExistente = 1L;
        CategoriaRequestDTO request = new CategoriaRequestDTO("Categoria Actualizada");

        Optional<CategoriaResponseDTO> actualizada = categoriaService.updateCategoria(idExistente, request);

        assertTrue(actualizada.isPresent());
        Assertions.assertEquals("Categoria Actualizada", actualizada.get().nombre());
    }

    @Test
    void deleteCategoria() {
        long idExistente = 2L;

        boolean eliminado = categoriaService.deleteCategoria(idExistente);

        assertTrue(eliminado);
        assertTrue(categoriaService.getCategoriaById(idExistente).isEmpty());
    }

    @Test
    void deleteCategoriaNoExistente() {
        long idInexistente = 999L;

        boolean eliminado = categoriaService.deleteCategoria(idInexistente);

        Assertions.assertFalse(eliminado);
    }
}