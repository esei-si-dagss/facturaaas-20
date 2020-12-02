package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class TipoIVADAOJPA extends GenericoDAOJPA<TipoIVA, Long> implements TipoIVADAO {

    @Override
    public List<TipoIVA> buscarActivos() {
        TypedQuery<TipoIVA> query = em.createQuery("SELECT t FROM TipoIVA AS t WHERE t.activo = TRUE", TipoIVA.class);
        return query.getResultList();
    }

    @Override
    public TipoIVA activar(TipoIVA tipoIVA) {
        tipoIVA.activar();
        return this.actualizar(tipoIVA);
    }

    @Override
    public TipoIVA desactivar(TipoIVA tipoIVA) {
        tipoIVA.desactivar();
        return this.actualizar(tipoIVA);
    }
    
}
