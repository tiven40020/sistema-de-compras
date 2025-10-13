package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
