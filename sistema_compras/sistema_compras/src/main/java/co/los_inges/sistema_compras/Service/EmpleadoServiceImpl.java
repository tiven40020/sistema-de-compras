package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Empleado;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpleadoServiceImpl implements  EmpleadoService{

    @Override
    public List<Empleado> getAllEmpleados() {
        return List.of();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEmpleado(long id) {
        return false;
    }

    @Override
    public Optional<Empleado> updateEmpleado(long id, Empleado empleado) {
        return Optional.empty();
    }
}
