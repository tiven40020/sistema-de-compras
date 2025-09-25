package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Long, Producto> {
}
