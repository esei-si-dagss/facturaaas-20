package es.uvigo.esei.dagss.facturaaas.controladores;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("loginController")
@RequestScoped
public class LoginController implements Serializable {

    private String login;
    private String password;

    @Inject
    AutenticacionController autenticacionController;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String doLogin() {
        if ((login == null) || login.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha indicado login", "No se ha indicado login"));
            return null; // Recargar página
        }

        if (autenticacionController.autenticarUsuario(login, password)) {
            autenticacionController.vincularRolUsuarioASesion();
            return autenticacionController.redireccionarPaginaInicial();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la autenticación", "Error en la autenticación"));
            return null; // Recargar página
        }
    }

}
