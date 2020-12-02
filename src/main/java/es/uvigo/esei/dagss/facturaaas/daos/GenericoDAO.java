package es.uvigo.esei.dagss.facturaaas.daos;

import java.util.List;

public interface GenericoDAO<T,K> {
    public T crear(T entidad);
    public T actualizar(T entidad);
    public void eliminar(T entidad);
    public T buscarPorClave(K clave);
    public List<T> buscarTodos();
    public Long contarTodos();
}
