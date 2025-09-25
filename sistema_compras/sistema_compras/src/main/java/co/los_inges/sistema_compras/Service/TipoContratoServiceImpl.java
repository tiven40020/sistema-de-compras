package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.TipoContrato;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TipoContratoServiceImpl implements TipoContratoService{

    @Override
    public List<TipoContrato> getAllTipoContratos() {
        return List.of();
    }

    @Override
    public Optional<TipoContrato> getTipoContratoById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteTipoContrato(long id) {
        return false;
    }

    @Override
    public Optional<TipoContrato> updateTipoContrato(long id, TipoContrato tipoContrato) {
        return Optional.empty();
    }
}
