package es.uvigo.esei.dagss.facturaaas.controladores.usuario;

import es.uvigo.esei.dagss.facturaaas.controladores.AutenticacionController;
import es.uvigo.esei.dagss.facturaaas.daos.ClienteDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.Cliente;
import es.uvigo.esei.dagss.facturaaas.entidades.Direccion;
import es.uvigo.esei.dagss.facturaaas.servicios.AutenticacionService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "clientesController")
@ViewScoped
public class ClientesController implements Serializable {

    private List<Cliente> clientes;
    private Cliente clienteActual;
    private boolean esNuevo;
    private String textoBusqueda;

   
    @Inject
    private ClienteDAO dao;

    @Inject
    private AutenticacionController autenticacionController;

        
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void setClienteActual(Cliente clienteActual) {
        this.clienteActual = clienteActual;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public String getTextoBusqueda() {
        return textoBusqueda;
    }

    public void setTextoBusqueda(String textoBusqueda) {
        this.textoBusqueda = textoBusqueda;
    }
    
    

    @PostConstruct
    public void cargaInicial() {
        this.clientes = refrescarLista();
        this.clienteActual = null;
        this.esNuevo = false;
    }

    
    public void doBuscarPorNombre() {
        this.clientes = dao.buscarPorNombreConPropietario(autenticacionController.getUsuarioLogueado(), textoBusqueda);
    }

    public void doBuscarPorLocalidad() {
        this.clientes = dao.buscarPorLocalidadConPropietario(autenticacionController.getUsuarioLogueado(), textoBusqueda);
    }
    
    public void doBuscarTodos() {
        this.clientes = refrescarLista();
    }
    
    
    public void doNuevo() {
        this.esNuevo = true;
        this.clienteActual = new Cliente();
        this.clienteActual.setPropietario(autenticacionController.getUsuarioLogueado());
        this.clienteActual.setDireccion(new Direccion("","","",""));
    }

    public void doEditar(Cliente cliente) {
        this.esNuevo = false;
        this.clienteActual = cliente;
    }


    public void doGuardarEditado() {
        if (this.esNuevo) {
            dao.crear(clienteActual);
        } else {
            dao.actualizar(clienteActual);
        }
        this.clientes = refrescarLista();
        this.clienteActual = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.clienteActual = null;
        this.esNuevo = false;
    }

    private List<Cliente> refrescarLista() {
        return dao.buscarTodosConPropietario(autenticacionController.getUsuarioLogueado());
    }

}
