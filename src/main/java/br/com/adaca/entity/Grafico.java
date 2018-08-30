package br.com.adaca.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_grafico")
@NamedQueries({
    @NamedQuery(name = "Grafico.findAll", query = "SELECT g FROM Grafico g")
    , @NamedQuery(name = "Grafico.findById", query = "SELECT g FROM Grafico g WHERE g.id = :id")
    , @NamedQuery(name = "Grafico.findByTipografico", query = "SELECT g FROM Grafico g WHERE g.tipografico = :tipografico")
    , @NamedQuery(name = "Grafico.findByDatagerado", query = "SELECT g FROM Grafico g WHERE g.datagerado = :datagerado")})
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
    private Autista idautista;
    @JoinColumn(name = "IDADMINISTRADOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Administrador idadministrador;

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

    public Autista getIdautista() {
        return idautista;
    }

    public void setIdautista(Autista idautista) {
        this.idautista = idautista;
    }

    public Administrador getIdadministrador() {
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
        return "br.com.adaca.entity.Grafico[ id=" + id + " ]";
    }
}
