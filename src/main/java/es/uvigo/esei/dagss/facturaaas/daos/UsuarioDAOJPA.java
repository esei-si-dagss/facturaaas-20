package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UsuarioDAOJPA extends GenericoDAOJPA<Usuario, Long> implements UsuarioDAO {

    @Override
    public Usuario buscarPorLogin(String login) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario AS u WHERE u.login = :login", Usuario.class);
        query.setParameter("login", login);
        List<Usuario> resultados = query.getResultList();
        if ((resultados != null) && (!resultados.isEmpty())) {
            return resultados.get(0);
        }
        return null;  // No encontrado
    }
    
    @Override
    public List<Usuario> buscarPorNombre(String patron) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario AS u WHERE u.nombre LIKE :patron", Usuario.class);
        query.setParameter("patron", "%"+patron+"%");
        return query.getResultList();
    }

    @Override
    public Usuario activar(Usuario usuario) {
        usuario.activar();
        return this.actualizar(usuario);
    }

    @Override
    public Usuario desactivar(Usuario usuario) {
        usuario.desactivar();
        return this.activar(usuario);
    }

}
