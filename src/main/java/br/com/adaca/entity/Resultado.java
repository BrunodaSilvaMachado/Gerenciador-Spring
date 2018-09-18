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
@Table(name = "tb_resultado")
public class Resultado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    private int dicas;
    @Basic(optional = false)
    @Column(nullable = false)
    private int cliquecerto;
    @Basic(optional = false)
    @Column(nullable = false)
    private int cliqueerrado;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mouseclique;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String mousedrag;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 16777215)
    private String mousepos;
    @Basic(optional = false)
    @Lob
    @Column(nullable = false, length = 65535)
    private String poserrado;
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

    public Resultado() {
    }

    public Resultado(Integer id) {
        this.id = id;
    }

    public Resultado(Integer id, int dicas, int cliquecerto, int cliqueerrado, String mouseclique, String mousedrag, String mousepos, String poserrado, Date horainicio, Date horafim) {
        this.id = id;
        this.dicas = dicas;
        this.cliquecerto = cliquecerto;
        this.cliqueerrado = cliqueerrado;
        this.mouseclique = mouseclique;
        this.mousedrag = mousedrag;
        this.mousepos = mousepos;
        this.poserrado = poserrado;
        this.horainicio = horainicio;
        this.horafim = horafim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDicas() {
        return dicas;
    }

    public void setDicas(int dicas) {
        this.dicas = dicas;
    }

    public int getCliquecerto() {
        return cliquecerto;
    }

    public void setCliquecerto(int cliquecerto) {
        this.cliquecerto = cliquecerto;
    }

    public int getCliqueerrado() {
        return cliqueerrado;
    }

    public void setCliqueerrado(int cliqueerrado) {
        this.cliqueerrado = cliqueerrado;
    }

    public String getMouseclique() {
        return mouseclique;
    }

    public void setMouseclique(String mouseclique) {
        this.mouseclique = mouseclique;
    }

    public String getMousedrag() {
        return mousedrag;
    }

    public void setMousedrag(String mousedrag) {
        this.mousedrag = mousedrag;
    }

    public String getMousepos() {
        return mousepos;
    }

    public void setMousepos(String mousepos) {
        this.mousepos = mousepos;
    }

    public String getPoserrado() {
        return poserrado;
    }

    public void setPoserrado(String poserrado) {
        this.poserrado = poserrado;
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
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Resultado[ id=" + id + " ]";
    }
}
