package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.FormaPago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class FormaPagoDAOJPA extends GenericoDAOJPA<FormaPago, Long> implements FormaPagoDAO {

    @Override
    public List<FormaPago> buscarActivas() {
        TypedQuery<FormaPago> query = em.createQuery("SELECT fp FROM FormaPago AS fp WHERE fp.activo = TRUE", FormaPago.class);
        return query.getResultList();
    }

    @Override
    public FormaPago activar(FormaPago formaPago) {
        formaPago.activar();
        return this.actualizar(formaPago);
    }

    @Override
    public FormaPago desactivar(FormaPago formaPago) {
        formaPago.desactivar();
        return this.actualizar(formaPago);
    }
    
}
