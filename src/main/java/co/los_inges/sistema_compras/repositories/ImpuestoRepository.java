package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {
}
