package es.uvigo.esei.dagss.facturaaas.daos;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericoDAOJPA<T, K> implements GenericoDAO<T, K> {

    @PersistenceContext(unitName = "facturaaas_PU")
    protected EntityManager em;
    
    protected Class<T> claseEntidad;

    private void establecerClaseEntidad() {
        // Identifica la clase de las entidades gestionada por este objeto (T.class)
        this.claseEntidad = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T crear(T entidad) {
        em.persist(entidad); // Crea una nueva tupla en la BD con los datos de "entidad"
        // -> con clave primarias autogeneradas, se generara su ID en el "commit" contra la BD
        return entidad;
    }

    @Override
    public T actualizar(T entidad) {
        return em.merge(entidad);   // Actualiza los datos de la "entidad" en su correspondiente tupla BD
    }

    @Override
    public void eliminar(T entidad) {
        em.remove(em.merge(entidad));  // Actualiza y elimina
    }

    @Override
    public T buscarPorClave(K clave) {
        if (this.claseEntidad == null) {
            establecerClaseEntidad();
        }
        return em.find(this.claseEntidad, clave);
    }

    @Override
    public List<T> buscarTodos() {
        if (this.claseEntidad == null) {
            establecerClaseEntidad();
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(this.claseEntidad);
        query.select(query.from(this.claseEntidad));
        return em.createQuery(query).getResultList();
    }

    @Override
    public Long contarTodos() {
        if (this.claseEntidad == null) {
            establecerClaseEntidad();
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(this.claseEntidad)));
        return em.createQuery(query).getSingleResult();
    }

}
