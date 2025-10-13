package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
