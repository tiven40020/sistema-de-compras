package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Cargo;
import java.util.List;
import java.util.Optional;


public interface CargoService {

    List<Cargo> getAllCargos();

    Optional<Cargo> getCargoById(long id);

    boolean deleteCargo(long id);

    Optional<Cargo> updateCargo(long id, Cargo cargo);

}
