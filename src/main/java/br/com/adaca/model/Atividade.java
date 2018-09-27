package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_atividade")
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String nome;
    @Column(length = 80)
    private String classificacao;
    private Integer nivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatividade")
    private List<br.com.adaca.model.Labirinto> labirintoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatividade")
    private List<br.com.adaca.model.Resultado> resultadoList;

    public Atividade() {
    }

    public Atividade(Integer id) {
        this.id = id;
    }

    public Atividade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<br.com.adaca.model.Labirinto> getLabirintoList() {
        return labirintoList;
    }

    public void setLabirintoList(List<Labirinto> labirintoList) {
        this.labirintoList = labirintoList;
    }

    public List<br.com.adaca.model.Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Atividade[ id=" + id + " ]";
    }
}
