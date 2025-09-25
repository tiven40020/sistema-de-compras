package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContratoRepository extends JpaRepository<Long, TipoContrato> {
}
