package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Long, Empleado> {
}
