package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Departamento;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

    @Override
    public List<Departamento> getAllDepartamentos() {
        return List.of();
    }

    @Override
    public Optional<Departamento> getDepartamentoById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteDepartamento(long id) {
        return false;
    }

    @Override
    public Optional<Departamento> updateDepartamento(long id, Departamento departamento) {
        return Optional.empty();
    }
}
