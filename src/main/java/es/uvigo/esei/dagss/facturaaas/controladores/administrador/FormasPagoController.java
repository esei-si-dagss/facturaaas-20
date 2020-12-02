package es.uvigo.esei.dagss.facturaaas.controladores.administrador;

import es.uvigo.esei.dagss.facturaaas.daos.FormaPagoDAO;
import es.uvigo.esei.dagss.facturaaas.entidades.FormaPago;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "formasPagoController")
@ViewScoped
public class FormasPagoController implements Serializable {

    private List<FormaPago> formasPago;
    private FormaPago formaPagoActual;
    private boolean esNueva;

    @Inject
    private FormaPagoDAO dao;

    public List<FormaPago> getFormasPago() {
        return formasPago;
    }

    public void setFormasPago(List<FormaPago> formasPago) {
        this.formasPago = formasPago;
    }

    public FormaPago getFormaPagoActual() {
        return formaPagoActual;
    }

    public void setFormaPagoActual(FormaPago formaPagoActual) {
        this.formaPagoActual = formaPagoActual;
    }

    public boolean isEsNueva() {
        return esNueva;
    }

    public void setEsNueva(boolean esNueva) {
        this.esNueva = esNueva;
    }

    @PostConstruct
    public void cargaInicial() {
        this.formasPago = refrescarLista();
        this.formaPagoActual = null;
        this.esNueva = false;
    }

    public void doNueva() {
        this.esNueva = true;
        this.formaPagoActual = new FormaPago();
    }

    public void doEditar(FormaPago formaPago) {
        this.esNueva = false;
        this.formaPagoActual = formaPago;
    }

    public void doEliminar(FormaPago formaPago) {
        dao.desactivar(formaPago);
        this.formasPago = refrescarLista();
    }

    public void doGuardarEditado() {
        if (this.esNueva) {
            dao.crear(formaPagoActual);
        } else {
            dao.actualizar(formaPagoActual);
        }
        this.formasPago = refrescarLista();
        this.formaPagoActual = null;
        this.esNueva = false;
    }

    public void doCancelarEditado() {
        this.formaPagoActual = null;
        this.esNueva = false;
    }

    private List<FormaPago> refrescarLista() {
        return dao.buscarTodos();
    }

}
