package es.uvigo.esei.dagss.facturaaas.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DATOS_FACTURACION")
public class DatosFacturacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;
    
    private String nombreComercial;
    private String nif;
    private Direccion direccion;
    private String telefono;
    private String email;
    private String cuentaBancaria;
    
    @Enumerated(EnumType.STRING)
    private TipoFacturacion tipo;
    
    @ManyToOne
    private FormaPago formaPagoPorDefecto;
    
    @ManyToOne
    private TipoIVA tipoIVAPorDefecto;

    public DatosFacturacion() {
    }

    public DatosFacturacion(Usuario usuario, String nombreComercial, String nif, Direccion direccion, String telefono, String email, String cuentaBancaria, TipoFacturacion tipo, FormaPago formaPagoPorDefecto, TipoIVA tipoIVAPorDefecto) {
        this.usuario = usuario;
        this.nombreComercial = nombreComercial;
        this.nif = nif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentaBancaria = cuentaBancaria;
        this.tipo = tipo;
        this.formaPagoPorDefecto = formaPagoPorDefecto;
        this.tipoIVAPorDefecto = tipoIVAPorDefecto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public TipoFacturacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoFacturacion tipo) {
        this.tipo = tipo;
    }

    public FormaPago getFormaPagoPorDefecto() {
        return formaPagoPorDefecto;
    }

    public void setFormaPagoPorDefecto(FormaPago formaPagoPorDefecto) {
        this.formaPagoPorDefecto = formaPagoPorDefecto;
    }

    public TipoIVA getTipoIVAPorDefecto() {
        return tipoIVAPorDefecto;
    }

    public void setTipoIVAPorDefecto(TipoIVA tipoIVAPorDefecto) {
        this.tipoIVAPorDefecto = tipoIVAPorDefecto;
    }
    
    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hashCode(this.id);
        } else {
            return hashCodePorContenido();
        }        
    }

    @Override
    public boolean equals(Object obj) {
        if (id !=null) {
            return equalsPorId(obj);
        } else {
            return equalsPorContenido(obj);
        }
    }    

    public boolean equalsPorId(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatosFacturacion other = (DatosFacturacion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public int hashCodePorContenido() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.nombreComercial);
        hash = 97 * hash + Objects.hashCode(this.nif);
        return hash;
    }

    public boolean equalsPorContenido(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DatosFacturacion other = (DatosFacturacion) obj;
        if (!Objects.equals(this.nombreComercial, other.nombreComercial)) {
            return false;
        }
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        return true;
    }
    
    
    
}
