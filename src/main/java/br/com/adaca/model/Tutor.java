package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_tutor", uniqueConstraints = {@UniqueConstraint(columnNames = {"USUARIO"})})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 50)
    private String usuario;
    @Column(nullable = false, length = 100)
    private String senha;
    @Column(nullable = false, length = 50)
    private String area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<br.com.adaca.model.Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<br.com.adaca.model.Configuracao> configuracaoList;

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

    public List<br.com.adaca.model.Sessao> getSessaoList() {
        return sessaoList;
    }

    public void setSessaoList(List<Sessao> sessaoList) {
        this.sessaoList = sessaoList;
    }

    public List<br.com.adaca.model.Configuracao> getConfiguracaoList() {
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
        return "Tutor[ id=" + id + " ]";
    }
}
