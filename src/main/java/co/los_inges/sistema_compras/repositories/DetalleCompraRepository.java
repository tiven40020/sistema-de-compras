package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
}
