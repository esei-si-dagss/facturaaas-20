package es.uvigo.esei.dagss.facturaaas.autenticacion;

import es.uvigo.esei.dagss.facturaaas.entidades.RolUsuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Filtro de URL para evitar el acceso a las "zonas privadas" de otros tipos de
 * usuarios por parte de usuarios no autenticados o usuarios autenticado de otro
 * tipo
 *
 * @author ribadas
 */
@WebFilter(filterName = "FiltroAutenticacion",
        urlPatterns = {"/faces/administrador/*", "/faces/usuario/*"})
public class FiltroAutenticacion implements Filter {

    public final static String ATRIBUTO_SESION_ROL_LOGUEADO = "rolUsuarioLogueado";

    public FiltroAutenticacion() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Recuperar el path de la URL y extraer el tipo
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        RolUsuario tipoUsuarioPeticion = extraerTipoUsuarioPeticion(httpServletRequest.getPathInfo());

        RolUsuario tipoUsuarioLogueado = null;  // Not logged
        HttpSession session = httpServletRequest.getSession();
        if (session != null) {
            tipoUsuarioLogueado = (RolUsuario) session.getAttribute(ATRIBUTO_SESION_ROL_LOGUEADO);
        }

        if (tipoUsuarioPeticion == null) { // Public URL
            // Dejar continuar la petición
            chain.doFilter(request, response);
        } else if (tipoUsuarioLogueado != null) { // An user was authenticated
            if (tipoUsuarioPeticion.equals(tipoUsuarioLogueado)) {
                // Dejar continuar la petición
                chain.doFilter(request, response);
            } else {
                // Finalizar la sesión del usuario (¿demasiado extrema?)
                httpServletRequest.getSession().invalidate();

                // TODO: loguear el intento de acceso
                // Redirir a la página de error de login
                String contextPath = httpServletRequest.getContextPath();
                ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login-error.xhtml");
            }
        } else {
            // TODO: loguear el intento de acceso
            // Redirir a la página de error de login
            String contextPath = httpServletRequest.getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/login-error.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private RolUsuario extraerTipoUsuarioPeticion(String path) {
        if (path.contains("/administrador/")) {
            return RolUsuario.ADMINISTRADOR;
        }
        if (path.contains("/usuario/")) {
            return RolUsuario.USUARIO;
        }
        return null; // Non protected path
    }
}
