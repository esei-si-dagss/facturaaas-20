package es.uvigo.esei.dagss.facturaaas.controladores.administrador;

import es.uvigo.esei.dagss.facturaaas.daos.TipoIVADAO;
import es.uvigo.esei.dagss.facturaaas.entidades.TipoIVA;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "tiposIVAController")
@ViewScoped
public class TiposIVAController implements Serializable {

    private List<TipoIVA> tiposIVA;
    private TipoIVA tipoIVAActual;
    private boolean esNuevo;

    @Inject
    private TipoIVADAO dao;

    public List<TipoIVA> getTiposIVA() {
        return tiposIVA;
    }

    public void setTiposIVA(List<TipoIVA> tiposIVA) {
        this.tiposIVA = tiposIVA;
    }


    public TipoIVA getTipoIVAActual() {
        return tipoIVAActual;
    }

    public void setTipoIVAActual(TipoIVA tipoIVAActual) {
        this.tipoIVAActual = tipoIVAActual;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    @PostConstruct
    public void cargaInicial() {
        this.tiposIVA = refrescarLista();
        this.tipoIVAActual = null;
        this.esNuevo = false;
    }

    public void doNuevo() {
        this.esNuevo = true;
        this.tipoIVAActual = new TipoIVA();
    }

    public void doEditar(TipoIVA tipoIVA) {
        this.esNuevo = false;
        this.tipoIVAActual = tipoIVA;
    }

    public void doEliminar(TipoIVA tipoIVA) {
        dao.desactivar(tipoIVA);
        this.tiposIVA = refrescarLista();
    }

    public void doGuardarEditado() {
        if (this.esNuevo) {
            dao.crear(tipoIVAActual);
        } else {
            dao.actualizar(tipoIVAActual);
        }
        this.tiposIVA = refrescarLista();
        this.tipoIVAActual = null;
        this.esNuevo = false;
    }

    public void doCancelarEditado() {
        this.tipoIVAActual = null;
        this.esNuevo = false;
    }

    private List<TipoIVA> refrescarLista() {
        return dao.buscarTodos();
    }

}
