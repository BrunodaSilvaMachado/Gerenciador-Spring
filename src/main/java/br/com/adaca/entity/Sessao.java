package br.com.adaca.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_sessao")
@NamedQueries({
    @NamedQuery(name = "Sessao.findAll", query = "SELECT s FROM Sessao s")
    , @NamedQuery(name = "Sessao.findById", query = "SELECT s FROM Sessao s WHERE s.id = :id")
    , @NamedQuery(name = "Sessao.findByDatalogin", query = "SELECT s FROM Sessao s WHERE s.datalogin = :datalogin")
    , @NamedQuery(name = "Sessao.findByDatalogout", query = "SELECT s FROM Sessao s WHERE s.datalogout = :datalogout")})
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datalogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datalogout;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Labirinto> labirintoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Resultado> resultadoList;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;
    @JoinColumn(name = "IDTUTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tutor idtutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Configuracao> configuracaoList;

    public Sessao() {
    }

    public Sessao(Integer id) {
        this.id = id;
    }

    public Sessao(Integer id, Date datalogin) {
        this.id = id;
        this.datalogin = datalogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatalogin() {
        return datalogin;
    }

    public void setDatalogin(Date datalogin) {
        this.datalogin = datalogin;
    }

    public Date getDatalogout() {
        return datalogout;
    }

    public void setDatalogout(Date datalogout) {
        this.datalogout = datalogout;
    }

    public List<Labirinto> getLabirintoList() {
        return labirintoList;
    }

    public void setLabirintoList(List<Labirinto> labirintoList) {
        this.labirintoList = labirintoList;
    }

    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    public Autista getIdautista() {
        return idautista;
    }

    public void setIdautista(Autista idautista) {
        this.idautista = idautista;
    }

    public Tutor getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(Tutor idtutor) {
        this.idtutor = idtutor;
    }

    public List<Configuracao> getConfiguracaoList() {
        return configuracaoList;
    }

    public void setConfiguracaoList(List<Configuracao> configuracaoList) {
        this.configuracaoList = configuracaoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sessao)) {
            return false;
        }
        Sessao other = (Sessao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Sessao[ id=" + id + " ]";
    }
}
