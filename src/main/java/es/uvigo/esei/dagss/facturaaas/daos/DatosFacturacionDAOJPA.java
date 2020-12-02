package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.DatosFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class DatosFacturacionDAOJPA extends GenericoDAOJPA<DatosFacturacion, Long> implements DatosFacturacionDAO {

    @Override
    public DatosFacturacion buscarConPropietario(Usuario propietario) {
        TypedQuery<DatosFacturacion> query = em.createQuery("SELECT df FROM DatosFacturacion AS df WHERE df.usuario.id = :idPropietario", DatosFacturacion.class);
        query.setParameter("idPropietario", propietario.getId());
        List<DatosFacturacion> resultado = query.getResultList();
        if ((resultado != null) && !resultado.isEmpty()) {
            return resultado.get(0);
        } else {
            return null;
        }
    }
    
}
