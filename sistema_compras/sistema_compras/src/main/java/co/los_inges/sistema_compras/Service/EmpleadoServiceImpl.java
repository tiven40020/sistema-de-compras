package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Empleado;
import co.los_inges.sistema_compras.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmpleadoServiceImpl implements  EmpleadoService{

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl (EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados()
    {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(long id)
    {
        return empleadoRepository.findById(id);
    }

    @Override
    public boolean deleteEmpleado(long id)
    {
        if(empleadoRepository.existsById(id)){
            empleadoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Empleado> updateEmpleado(long id, Empleado empleado)
    {
        Optional<Empleado> empleadoExistente = empleadoRepository.findById(id);

        if(empleadoExistente.isPresent()){
            Empleado aux = empleadoExistente.get();
            if(empleado.getNombre() != null && !empleado.getNombre().isBlank()){
                aux.setNombre(empleado.getNombre());
            }
            if(empleado.getApellido() != null && !empleado.getApellido().isBlank()){
                aux.setApellido(empleado.getApellido());
            }
            if(empleado.getNumeroDocumento() != null && !empleado.getNumeroDocumento().isBlank()){
                aux.setNumeroDocumento(empleado.getNumeroDocumento());
            }
            if(empleado.getCorreo() != null && !empleado.getCorreo().isBlank()){
                aux.setCorreo(empleado.getCorreo());
            }
            if(empleado.getTelefono() != null && !empleado.getTelefono().isBlank()){
                aux.setTelefono(empleado.getTelefono());
            }
            if(empleado.getDireccion() != null && !empleado.getDireccion().isBlank()){
                aux.setDireccion(empleado.getDireccion());
            }
            if(empleado.getFechaIngreso() != null){
                aux.setFechaIngreso(empleado.getFechaIngreso());
            }
            if (empleado.getDepartamento() != null) {
                aux.setDepartamento(empleado.getDepartamento());
            }
            if (empleado.getCargo() != null) {
                aux.setCargo(empleado.getCargo());
            }
            if(empleado.getTipoContrato() != null){
                aux.setTipoContrato(empleado.getTipoContrato());
            }
            empleadoRepository.save(aux);
            return Optional.of(aux);
        }
        return Optional.empty();
    }
}
