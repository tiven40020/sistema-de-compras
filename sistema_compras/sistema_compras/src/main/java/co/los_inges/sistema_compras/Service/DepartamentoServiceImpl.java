package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Departamento;
import co.los_inges.sistema_compras.repositories.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

    private DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl (DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }
    @Override
    public List<Departamento> getAllDepartamentos()
    {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> getDepartamentoById(long id)
    {
        return departamentoRepository.findById(id);
    }

    @Override
    public boolean deleteDepartamento(long id)
    {
        if(departamentoRepository.existsById(id)){
            departamentoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Departamento> updateDepartamento(long id, Departamento departamento)
    {
        Optional<Departamento> DepartamentoExistente = departamentoRepository.findById(id);
        if(DepartamentoExistente.isPresent()){
            Departamento aux = DepartamentoExistente.get();
            if(departamento.getNombre() != null && !departamento.getNombre().isBlank()) {
                aux.setNombre(departamento.getNombre());
            }
            if(departamento.getDescripcion() != null && !departamento.getDescripcion().isBlank()) {
                aux.setDescripcion(departamento.getDescripcion());
            }
            departamentoRepository.save(aux);
            return Optional.of(aux);
        }
        return Optional.empty();
    }
}
