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
@Table(name = "tb_labirinto")
public class Labirinto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int quanterro;
    @Basic(optional = false)
    @Column(nullable = false, length = 300)
    private String paredes;
    @Basic(optional = false)
    @Column(nullable = false)
    private int quantdicas;
    @Column(length = 500)
    private String posicaoerro;
    @Column(length = 500)
    private String teclaerrada;
    @Basic(optional = false)
    @Column(nullable = false)
    private int quantbotoes;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainicio;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horafim;
    @JoinColumn(name = "IDSESSAO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Sessao idsessao;
    @JoinColumn(name = "IDATIVIDADE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Atividade idatividade;

    public Labirinto() {
    }

    public Labirinto(Integer id) {
        this.id = id;
    }

    public Labirinto(Integer id, int quanterro, String paredes, int quantdicas, int quantbotoes, Date horainicio, Date horafim) {
        this.id = id;
        this.quanterro = quanterro;
        this.paredes = paredes;
        this.quantdicas = quantdicas;
        this.quantbotoes = quantbotoes;
        this.horainicio = horainicio;
        this.horafim = horafim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuanterro() {
        return quanterro;
    }

    public void setQuanterro(int quanterro) {
        this.quanterro = quanterro;
    }

    public String getParedes() {
        return paredes;
    }

    public void setParedes(String paredes) {
        this.paredes = paredes;
    }

    public int getQuantdicas() {
        return quantdicas;
    }

    public void setQuantdicas(int quantdicas) {
        this.quantdicas = quantdicas;
    }

    public String getPosicaoerro() {
        return posicaoerro;
    }

    public void setPosicaoerro(String posicaoerro) {
        this.posicaoerro = posicaoerro;
    }

    public String getTeclaerrada() {
        return teclaerrada;
    }

    public void setTeclaerrada(String teclaerrada) {
        this.teclaerrada = teclaerrada;
    }

    public int getQuantbotoes() {
        return quantbotoes;
    }

    public void setQuantbotoes(int quantbotoes) {
        this.quantbotoes = quantbotoes;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafim() {
        return horafim;
    }

    public void setHorafim(Date horafim) {
        this.horafim = horafim;
    }

    public Sessao getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(Sessao idsessao) {
        this.idsessao = idsessao;
    }

    public Atividade getIdatividade() {
        return idatividade;
    }

    public void setIdatividade(Atividade idatividade) {
        this.idatividade = idatividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Labirinto)) {
            return false;
        }
        Labirinto other = (Labirinto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Labirinto[ id=" + id + " ]";
    }
}
