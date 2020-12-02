package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class ClienteDAOJPA extends GenericoDAOJPA<Cliente, Long> implements ClienteDAO {

    @Override
    public List<Cliente> buscarTodosConPropietario(Usuario propietario) {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente AS c WHERE c.propietario.id = :idPropietario", Cliente.class);
        query.setParameter("idPropietario", propietario.getId());
        return query.getResultList();
    }

    @Override
    public List<Cliente> buscarPorNombreConPropietario(Usuario propietario, String patron) {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente AS c "+
                                                   "WHERE c.propietario.id = :idPropietario "+
                                                   "AND c.nombre LIKE :patron", Cliente.class);
        query.setParameter("idPropietario", propietario.getId());
        query.setParameter("patron", "%"+patron+"%");        
        return query.getResultList();
    }

    @Override
    public List<Cliente> buscarPorLocalidadConPropietario(Usuario propietario, String localidad) {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente AS c "+
                                                   "WHERE c.propietario.id = :idPropietario "+
                                                   "AND c.direccion.localidad = :localidad", Cliente.class);
        query.setParameter("idPropietario", propietario.getId());
        query.setParameter("localidad", localidad);        
        return query.getResultList();
    }
    
}
