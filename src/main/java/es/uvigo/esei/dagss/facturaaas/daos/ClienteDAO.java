package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.List;

public interface ClienteDAO extends GenericoDAO<Cliente, Long> {

    public List<Cliente> buscarTodosConPropietario(Usuario propietario);

    public List<Cliente> buscarPorNombreConPropietario(Usuario propietario, String patron);

    public List<Cliente> buscarPorLocalidadConPropietario(Usuario propietario, String localidad);

}
