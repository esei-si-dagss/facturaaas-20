package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import java.util.List;

public interface TipoIVADAO extends GenericoDAO<TipoIVA, Long>{
    public List<TipoIVA> buscarActivos();
    public TipoIVA activar(TipoIVA tipoIVA);
    public TipoIVA desactivar(TipoIVA tipoIVA);
}
