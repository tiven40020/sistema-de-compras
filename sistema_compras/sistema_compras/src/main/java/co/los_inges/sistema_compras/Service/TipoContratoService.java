package co.los_inges.sistema_compras.Service;

import co.los_inges.sistema_compras.models.TipoContrato;
import java.util.*;

public interface TipoContratoService {

    List<TipoContrato> getAllTipoContratos();

    Optional<TipoContrato> getTipoContratoById(long id);

    boolean deleteTipoContrato(long id);

    Optional<TipoContrato> updateTipoContrato(long id, TipoContrato tipoContrato);
}
