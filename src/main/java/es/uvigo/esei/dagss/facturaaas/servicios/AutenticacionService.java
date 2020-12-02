package es.uvigo.esei.dagss.facturaaas.servicios;

import es.uvigo.esei.dagss.facturaaas.daos.UsuarioDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.RolUsuario;
import es.uvigo.esei.dagss.facturaaas.entidades.Usuario;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AutenticacionService {

    @Inject
    UsuarioDAO usuarioDAO;

    public boolean autenticarUsuario(String login, String plainPassword) {
        return this.autenticarUsuario(login, plainPassword, null);
    }

    public boolean autenticarUsuario(String login, String plainPassword, RolUsuario rol) {
        boolean autenticado = false;
        Usuario usuario = usuarioDAO.buscarPorLogin(login);
        if (usuario != null) {
            if (comprobarRol(rol, usuario.getRol())) {
                if (comprobarPassword(plainPassword, usuario.getPassword())) {
                    autenticado = true;
                }
            }
        }
        return autenticado;
    }

    private boolean comprobarRol(RolUsuario roleToCheck, RolUsuario userRole) {
        if (roleToCheck == null) {
            return true;
        } else {
            return roleToCheck.equals(userRole);
        }
    }

    private boolean comprobarPassword(String passwordToCheck, String userPasswordEncoded) {
        if (userPasswordEncoded == null) { // Sin password establecido -> acceso  (mejorable)
            return true;
        } else {
            if (passwordToCheck != null) {
                if (passwordToCheck.isEmpty() && !userPasswordEncoded.isEmpty()) {
                    return false;
                } else {
                    return PasswordHelper.checkPassword(passwordToCheck, userPasswordEncoded);
                }
            }
        }
        return false;
    }

    public Usuario actualizarPassword(Long id, String newPassword) {
        Usuario usuario = usuarioDAO.buscarPorClave(id);
        if (usuario != null) {
            String newPasswordEncoded = PasswordHelper.encodePlainPassword(newPassword);
            usuario.setPassword(newPasswordEncoded);
            usuario = usuarioDAO.actualizar(usuario);
        }
        return usuario;
    }

    public Usuario actualizarUltimoAcceso(Long id) {
        Usuario usuario = usuarioDAO.buscarPorClave(id);
        if (usuario != null) {
            Date fecha = Calendar.getInstance().getTime();
            usuario.setAcceso(fecha);
            usuario = usuarioDAO.actualizar(usuario);
        }
        return usuario;
    }
    
    public boolean existeLogin(String login) {
        return (usuarioDAO.buscarPorLogin(login) != null);
    }    
}
