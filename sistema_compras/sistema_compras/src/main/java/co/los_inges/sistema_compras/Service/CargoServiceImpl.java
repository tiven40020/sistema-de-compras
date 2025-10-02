package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Cargo;
import co.los_inges.sistema_compras.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Override
    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public Optional<Cargo> getCargoById(long id) {
        return cargoRepository.findById(id);
    }

    @Override
    public boolean deleteCargo(long id) {
        if (cargoRepository.existsById(id)) {
            cargoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Cargo> updateCargo(long id, Cargo cargo) {
        Optional<Cargo> cargoExistente = cargoRepository.findById(id);

        if (cargoExistente.isPresent()) {
            Cargo aux = cargoExistente.get();

            if (cargo.getNombre() != null && !cargo.getNombre().isBlank())
                aux.setNombre(cargo.getNombre());

            if (cargo.getDescripcion() != null && !cargo.getDescripcion().isBlank())
                aux.setDescripcion(cargo.getDescripcion());

            cargoRepository.save(aux);
            return Optional.of(aux);
        } else {
            return Optional.empty();
        }

    }
}
