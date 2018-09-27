package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_grafico")
public class Grafico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false)
    private byte[] grafico;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String tipografico;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datagerado;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private br.com.adaca.model.Autista idautista;
    @JoinColumn(name = "IDADMINISTRADOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private br.com.adaca.model.Administrador idadministrador;

    public Grafico() {
    }

    public Grafico(Integer id) {
        this.id = id;
    }

    public Grafico(Integer id, byte[] grafico, String tipografico) {
        this.id = id;
        this.grafico = grafico;
        this.tipografico = tipografico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getGrafico() {
        return grafico;
    }

    public void setGrafico(byte[] grafico) {
        this.grafico = grafico;
    }

    public String getTipografico() {
        return tipografico;
    }

    public void setTipografico(String tipografico) {
        this.tipografico = tipografico;
    }

    public Date getDatagerado() {
        return datagerado;
    }

    public void setDatagerado(Date datagerado) {
        this.datagerado = datagerado;
    }

    public br.com.adaca.model.Autista getIdautista() {
        return idautista;
    }

    public void setIdautista(Autista idautista) {
        this.idautista = idautista;
    }

    public br.com.adaca.model.Administrador getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Administrador idadministrador) {
        this.idadministrador = idadministrador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Grafico)) {
            return false;
        }
        Grafico other = (Grafico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grafico[ id=" + id + " ]";
    }
}
