package es.uvigo.esei.dagss.facturaaas.daos;

import es.uvigo.esei.dagss.facturaaas.entidades.DatosFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;

public interface DatosFacturacionDAO extends GenericoDAO<DatosFacturacion, Long>{
    public DatosFacturacion buscarConPropietario(Usuario propietario);
}
