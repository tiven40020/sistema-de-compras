package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
