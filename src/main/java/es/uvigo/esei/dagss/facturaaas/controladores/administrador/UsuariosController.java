package es.uvigo.esei.dagss.facturaaas.controladores.administrador;

import es.uvigo.esei.dagss.facturaaas.daos.UsuarioDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.RolUsuario;
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

@Named(value = "usuariosController")
@ViewScoped
public class UsuariosController implements Serializable {

    private List<Usuario> usuarios;
    private Usuario usuarioActual;
    private boolean esNuevo;

    private RolUsuario[] rolesUsuario = RolUsuario.values();
    
    @Inject
    private UsuarioDAO dao;

    @Inject
    private AutenticacionService autenticacionService;

    public RolUsuario[] getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(RolUsuario[] rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }
        
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    @PostConstruct
    public void cargaInicial() {
        this.usuarios = refrescarLista();
        this.usuarioActual = null;
        this.esNuevo = false;
    }

    public void doNuevo() {
        this.esNuevo = true;
        this.usuarioActual = new Usuario();
    }

    public void doEditar(Usuario usuario) {
        this.esNuevo = false;
        this.usuarioActual = usuario;
    }

    public void doEliminar(Usuario usuario) {
        dao.desactivar(usuario);
        this.usuarios = refrescarLista();
    }

    public void doGuardarEditado() {
        if (this.esNuevo) {
            // Verificar login no existente
            if (autenticacionService.existeLogin(usuarioActual.getLogin())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. El login introducido ya existe", "Error. El login introducido ya existe"));
                return; // Repinta vista mostrando el error
            }
            dao.crear(usuarioActual);
        } else {
            dao.actualizar(usuarioActual);
        }
        this.usuarios = refrescarLista();
        this.usuarioActual = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.usuarioActual = null;
        this.esNuevo = false;
    }

    private List<Usuario> refrescarLista() {
        return dao.buscarTodos();
    }

}
