package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
