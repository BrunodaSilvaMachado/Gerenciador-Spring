package br.com.adaca.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "tb_tutor", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USUARIO"})})
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
    , @NamedQuery(name = "Tutor.findById", query = "SELECT t FROM Tutor t WHERE t.id = :id")
    , @NamedQuery(name = "Tutor.findByNome", query = "SELECT t FROM Tutor t WHERE t.nome = :nome")
    , @NamedQuery(name = "Tutor.findByUsuario", query = "SELECT t FROM Tutor t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "Tutor.findBySenha", query = "SELECT t FROM Tutor t WHERE t.senha = :senha")
    , @NamedQuery(name = "Tutor.findByArea", query = "SELECT t FROM Tutor t WHERE t.area = :area")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String usuario;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String senha;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<Configuracao> configuracaoList;

    public Tutor() {
    }

    public Tutor(Integer id) {
        this.id = id;
    }

    public Tutor(Integer id, String nome, String usuario, String senha, String area) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<Sessao> getSessaoList() {
        return sessaoList;
    }

    public void setSessaoList(List<Sessao> sessaoList) {
        this.sessaoList = sessaoList;
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
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Tutor[ id=" + id + " ]";
    }
}
