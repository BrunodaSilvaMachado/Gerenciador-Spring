package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_configuracao")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String som;
    @Basic(optional = false)
    @Column(nullable = false, length = 80)
    private String musica;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean dicatempo;
    @Basic(optional = false)
    @Column(nullable = false)
    private boolean dicacaminho;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String tipodica;
    @Basic(optional = false)
    @Column(nullable = false, length = 50)
    private String comemoracao;
    private Integer tempodica;
    @Basic(optional = false)
    @Column(nullable = false, length = 1000)
    private String menu;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;
    @JoinColumn(name = "IDTUTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tutor idtutor;
    @JoinColumn(name = "IDSESSAO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private br.com.adaca.model.Sessao idsessao;

    public Configuracao() {
    }

    public Configuracao(Integer id) {
        this.id = id;
    }

    public Configuracao(Integer id, String som, String musica, boolean dicatempo, boolean dicacaminho, String tipodica, String comemoracao, String menu) {
        this.id = id;
        this.som = som;
        this.musica = musica;
        this.dicatempo = dicatempo;
        this.dicacaminho = dicacaminho;
        this.tipodica = tipodica;
        this.comemoracao = comemoracao;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSom() {
        return som;
    }

    public void setSom(String som) {
        this.som = som;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public boolean getDicatempo() {
        return dicatempo;
    }

    public void setDicatempo(boolean dicatempo) {
        this.dicatempo = dicatempo;
    }

    public boolean getDicacaminho() {
        return dicacaminho;
    }

    public void setDicacaminho(boolean dicacaminho) {
        this.dicacaminho = dicacaminho;
    }

    public String getTipodica() {
        return tipodica;
    }

    public void setTipodica(String tipodica) {
        this.tipodica = tipodica;
    }

    public String getComemoracao() {
        return comemoracao;
    }

    public void setComemoracao(String comemoracao) {
        this.comemoracao = comemoracao;
    }

    public Integer getTempodica() {
        return tempodica;
    }

    public void setTempodica(Integer tempodica) {
        this.tempodica = tempodica;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
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

    public br.com.adaca.model.Sessao getIdsessao() {
        return idsessao;
    }

    public void setIdsessao(Sessao idsessao) {
        this.idsessao = idsessao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Configuracao)) {
            return false;
        }
        Configuracao other = (Configuracao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Configuracao[ id=" + id + " ]";
    }
}
