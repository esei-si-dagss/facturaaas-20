package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.FormaPago;
import java.util.List;

public interface FormaPagoDAO extends GenericoDAO<FormaPago, Long>{
    public List<FormaPago> buscarActivas();
    public FormaPago activar(FormaPago formaPago);
    public FormaPago desactivar(FormaPago formaPago);
}
