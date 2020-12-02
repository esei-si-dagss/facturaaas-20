package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.DatosFacturacionDAO;
import es.uvigo.esei.dagss.facturaaas.daos.FormaPagoDAO;
import es.uvigo.esei.dagss.facturaaas.daos.TipoIVADAO;
import es.uvigo.esei.dagss.facturaaas.daos.UsuarioDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.DatosFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.Direccion;
import es.uvigo.esei.dagss.facturaaas.entidades.FormaPago;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoFacturacion;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import es.uvigo.esei.dagss.facturaaas.servicios.AutenticacionService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "perfilController")
@ViewScoped
public class PerfilController implements Serializable {

    private Usuario usuarioActual;
    private DatosFacturacion datosFacturacionActual;
    private String password1;
    private String password2;

    private TipoFacturacion[] tiposFacturacion = TipoFacturacion.values();

    @Inject
    private AutenticacionController autenticacionController;

    @Inject
    private DatosFacturacionDAO datosFacturacionDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private AutenticacionService autenticacionService;

    @Inject
    private FormaPagoDAO formaPagoDAO;
    
    @Inject
    private TipoIVADAO tipoIVADAO;

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public DatosFacturacion getDatosFacturacionActual() {
        return datosFacturacionActual;
    }

    public void setDatosFacturacionActual(DatosFacturacion datosFacturacionActual) {
        this.datosFacturacionActual = datosFacturacionActual;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public TipoFacturacion[] getTiposFacturacion() {
        return tiposFacturacion;
    }

    public void setTiposFacturacion(TipoFacturacion[] tiposFacturacion) {
        this.tiposFacturacion = tiposFacturacion;
    }

    @PostConstruct
    public void inicializacion() {
        this.usuarioActual = autenticacionController.getUsuarioLogueado();
        this.datosFacturacionActual = cargarDatosFacturacion();
    }

    private DatosFacturacion cargarDatosFacturacion() {
        DatosFacturacion datos = datosFacturacionDAO.buscarConPropietario(usuarioActual);
        if (datos == null) { // Primer acceso, no existen DatosFacturacion -> crearlos
            datos = new DatosFacturacion();
            datos.setUsuario(usuarioActual);
            datos.setNombreComercial(usuarioActual.getNombre());
            datos.setEmail(usuarioActual.getEmail());
            datos.setDireccion(new Direccion("", "", "", ""));

            datos = datosFacturacionDAO.crear(datos);
        }
        return datos;
    }

    public List<FormaPago> listadoFormasPago() {
        return formaPagoDAO.buscarActivas();
    }
    
    public List<TipoIVA> listadoTiposIVA() {
        return tipoIVADAO.buscarActivos();
    }

    
    public void doGuardarDatosFacturacion() {
        DatosFacturacion nuevo = datosFacturacionDAO.actualizar(datosFacturacionActual);
        datosFacturacionActual = nuevo;
    }

    public void doCancelarDatosFacturacion() {
        datosFacturacionActual = cargarDatosFacturacion();
    }

    public void doGuardarDatosUsuario() {
        Usuario nuevo = usuarioDAO.actualizar(usuarioActual);
        usuarioActual = nuevo;
        autenticacionController.setUsuarioLogueado(usuarioActual);
    }

    public void doCancelarDatosUsuario() {
        usuarioActual = autenticacionController.getUsuarioLogueado();
    }

    public void doCambiarPassword() {
        if ((password1 != null) && (password2 != null) && password1.equals(password2)) {
            Usuario nuevo = autenticacionService.actualizarPassword(usuarioActual.getId(), password1);
            usuarioActual = nuevo;
            autenticacionController.setUsuarioLogueado(usuarioActual);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Los passswords introducidos no coinciden o son nulos", "Error.  Los passswords introducidos no coinciden o son nulos"));
        }
        password1 = "";
        password2 = "";

    }
}
