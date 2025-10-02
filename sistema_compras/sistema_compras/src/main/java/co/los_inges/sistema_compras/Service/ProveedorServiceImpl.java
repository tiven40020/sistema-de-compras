package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.Proveedor;
import co.los_inges.sistema_compras.repositories.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl (ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
    @Override
    public List<Proveedor> getAllProveedores()
    {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedorById(long id)
    {
        return proveedorRepository.findById(id);
    }

    @Override
    public boolean deleteProveedor(long id)
    {
        if(proveedorRepository.existsById(id)){
            proveedorRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Proveedor> updateProveedor(long id, Proveedor proveedor)
    {
        return Optional.empty();
    }
}
/*
private String nombre;
    private String nit;
    private String email;
    private String direccion;
 */
