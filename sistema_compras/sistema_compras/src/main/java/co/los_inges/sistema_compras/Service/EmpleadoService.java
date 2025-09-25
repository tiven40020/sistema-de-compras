package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Empleado;

import java.util.*;

public interface EmpleadoService {

    List<Empleado> getAllEmpleados();

    Optional<Empleado> getEmpleadoById(long id);

    boolean deleteEmpleado(long id);

    Optional<Empleado> updateEmpleado(long id, Empleado empleado);
}
