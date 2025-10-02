package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.FacturaProveedor;
import co.los_inges.sistema_compras.repositories.FacturaProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacturaProveedorServiceImpl implements FacturaProveedorService{


    private final FacturaProveedorRepository facturaProveedorRepository;

    public FacturaProveedorServiceImpl (FacturaProveedorRepository facturaProveedorRepository) {
        this.facturaProveedorRepository = facturaProveedorRepository;
    }

    @Override
    public List<FacturaProveedor> getAllFacturasProveedores()
    {
        return facturaProveedorRepository.findAll();
    }

    @Override
    public Optional<FacturaProveedor> getFacturaProveedorById(long id)
    {
        return facturaProveedorRepository.findById(id);
    }

    @Override
    public boolean deleteFacturaProveedor(long id)
    {
        if(facturaProveedorRepository.existsById(id)){
            facturaProveedorRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<FacturaProveedor> updateFacturaProveedor(long id, FacturaProveedor facturaProveedor)
    {
        Optional<FacturaProveedor> facturaProveedorExistente = facturaProveedorRepository.findById(id);

        if(facturaProveedorExistente.isPresent()){
            FacturaProveedor aux = facturaProveedorExistente.get();
            if(facturaProveedor.getProveedor() != null){
                aux.setProveedor(facturaProveedor.getProveedor());
            }
            if(facturaProveedor.getEmpleado() != null){
                aux.setEmpleado(facturaProveedor.getEmpleado());
            }
            if(facturaProveedor.getFecha() != null){
                aux.setFecha(facturaProveedor.getFecha());
            }
            if(facturaProveedor.getEstado() != null && !facturaProveedor.getEstado().isBlank()){
                aux.setEstado(facturaProveedor.getEstado());
            }
            if(facturaProveedor.getObservaciones() != null && !facturaProveedor.getObservaciones().isBlank()){
                aux.setObservaciones(facturaProveedor.getObservaciones());
            }
            if(facturaProveedor.getTotal() >= 0){
                aux.setTotal(facturaProveedor.getTotal());
            }
            facturaProveedorRepository.save(aux);
            return Optional.of(aux);
        }
        return Optional.empty();
    }
}

