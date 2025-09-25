package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Cargo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService{

    @Override
    public List<Cargo> getAllCargos() {
        return List.of();
    }

    @Override
    public Optional<Cargo> getCargoById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteCargo(long id) {
        return false;
    }

    @Override
    public Optional<Cargo> updateCargo(long id, Cargo cargo) {
        return Optional.empty();
    }
}
