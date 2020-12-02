package es.uvigo.esei.dagss.facturaaas.controladores;

import es.uvigo.esei.dagss.facturaaas.autenticacion.FiltroAutenticacion;
import es.uvigo.esei.dagss.facturaaas.daos.UsuarioDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.RolUsuario;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import es.uvigo.esei.dagss.facturaaas.servicios.AutenticacionService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named("autenticacionController")
@SessionScoped
public class AutenticacionController implements Serializable {

    private boolean estaAutenticado;
    private Usuario usuarioLogueado;

    @Inject
    AutenticacionService autenticacionService;

    @Inject
    UsuarioDAO usuarioDAO;

    @PostConstruct
    public void initialize() {
        this.estaAutenticado = false;
        this.usuarioLogueado = null;
    }


    public boolean isEstaAutenticado() {
        return estaAutenticado;
    }

    public void setEstaAutenticado(boolean estaAutenticado) {
        this.estaAutenticado = estaAutenticado;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }



    public String doLogout() {
        if (usuarioLogueado != null) {
            autenticacionService.actualizarUltimoAcceso(usuarioLogueado.getId());
        }
        estaAutenticado = false;
        usuarioLogueado = null;

        // Eliminar sesion  
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Volver a página principal
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean autenticarUsuario(String login, String plainPassword) {
        if (autenticacionService.autenticarUsuario(login, plainPassword)) {
            estaAutenticado = true;
            setUsuarioLogueado(usuarioDAO.buscarPorLogin(login));
            return true;
        } else {
            estaAutenticado = false;
            usuarioLogueado = null;
            return false;
        }
    }

    public void vincularRolUsuarioASesion() {
        if (this.estaAutenticado) {
            //Vincular Rol a la Sesion (HttpSession) -> consultado en FiltroAutenticacion para controlar acceso a URLs privadas
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute(FiltroAutenticacion.ATRIBUTO_SESION_ROL_LOGUEADO, this.usuarioLogueado.getRol());
        }
    }

    public String redireccionarPaginaInicial() {
        RolUsuario rol = this.usuarioLogueado.getRol();
        if (rol == null) {
            return "/index.xhtml?faces-redirect=true";
        }
        switch (rol) {
            case ADMINISTRADOR:
                return "/administrador/index.xhtml?faces-redirect=true";
            case USUARIO:
                return "/usuario/index.xhtml?faces-redirect=true";
            default:
                return "/index.xhtml?faces-redirect=true";
        }
    }

}
