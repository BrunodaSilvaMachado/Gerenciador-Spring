package br.com.adaca.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_medicamento")
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m")
    , @NamedQuery(name = "Medicamento.findById", query = "SELECT m FROM Medicamento m WHERE m.id = :id")
    , @NamedQuery(name = "Medicamento.findByNome", query = "SELECT m FROM Medicamento m WHERE m.nome = :nome")
    , @NamedQuery(name = "Medicamento.findByTipo", query = "SELECT m FROM Medicamento m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Medicamento.findByComposicao", query = "SELECT m FROM Medicamento m WHERE m.composicao = :composicao")
    , @NamedQuery(name = "Medicamento.findByLaboratorio", query = "SELECT m FROM Medicamento m WHERE m.laboratorio = :laboratorio")
    , @NamedQuery(name = "Medicamento.findByPosologia", query = "SELECT m FROM Medicamento m WHERE m.posologia = :posologia")
    , @NamedQuery(name = "Medicamento.findByObservacao", query = "SELECT m FROM Medicamento m WHERE m.observacao = :observacao")
    , @NamedQuery(name = "Medicamento.findByStats", query = "SELECT m FROM Medicamento m WHERE m.stats = :stats")})
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(length = 50)
    private String tipo;
    @Column(length = 70)
    private String composicao;
    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String laboratorio;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String posologia;
    @Column(length = 100)
    private String observacao;
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String stats;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;

    public Medicamento() {
    }

    public Medicamento(Integer id) {
        this.id = id;
    }

    public Medicamento(Integer id, String nome, String laboratorio, String posologia, String stats) {
        this.id = id;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.posologia = posologia;
        this.stats = stats;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComposicao() {
        return composicao;
    }

    public void setComposicao(String composicao) {
        this.composicao = composicao;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public Autista getIdautista() {
        return idautista;
    }

    public void setIdautista(Autista idautista) {
        this.idautista = idautista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Medicamento[ id=" + id + " ]";
    }
}
