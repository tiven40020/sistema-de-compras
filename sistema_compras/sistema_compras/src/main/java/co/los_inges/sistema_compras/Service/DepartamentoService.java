package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Departamento;
import java.util.*;

public interface DepartamentoService {


    List<Departamento> getAllDepartamentos();

    Optional<Departamento> getDepartamentoById(long id);

    boolean deleteDepartamento(long id);

    Optional<Departamento> updateDepartamento(long id, Departamento departamento);

}
