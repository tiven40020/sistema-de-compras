package co.los_inges.sistema_compras.repositories;

import co.los_inges.sistema_compras.models.ProveedorTelefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorTelefonoRepository extends JpaRepository<ProveedorTelefono, Long> {

    List<ProveedorTelefono> findByProveedorIdProveedor(Long idProveedor);
}
