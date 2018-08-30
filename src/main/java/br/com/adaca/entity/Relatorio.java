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
@Table(name = "tb_relatorio")
@NamedQueries({
    @NamedQuery(name = "Relatorio.findAll", query = "SELECT r FROM Relatorio r")
    , @NamedQuery(name = "Relatorio.findById", query = "SELECT r FROM Relatorio r WHERE r.id = :id")
    , @NamedQuery(name = "Relatorio.findByTiporelatorio", query = "SELECT r FROM Relatorio r WHERE r.tiporelatorio = :tiporelatorio")
    , @NamedQuery(name = "Relatorio.findByDatagerado", query = "SELECT r FROM Relatorio r WHERE r.datagerado = :datagerado")})
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false)
    private byte[] relatorio;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String tiporelatorio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datagerado;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;
    @JoinColumn(name = "IDADMINISTRADOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Administrador idadministrador;

    public Relatorio() {
    }

    public Relatorio(Integer id) {
        this.id = id;
    }

    public Relatorio(Integer id, byte[] relatorio, String tiporelatorio) {
        this.id = id;
        this.relatorio = relatorio;
        this.tiporelatorio = tiporelatorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(byte[] relatorio) {
        this.relatorio = relatorio;
    }

    public String getTiporelatorio() {
        return tiporelatorio;
    }

    public void setTiporelatorio(String tiporelatorio) {
        this.tiporelatorio = tiporelatorio;
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
        if (!(object instanceof Relatorio)) {
            return false;
        }
        Relatorio other = (Relatorio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Relatorio[ id=" + id + " ]";
    }
}
