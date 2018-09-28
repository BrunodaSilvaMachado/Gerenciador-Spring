package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_autista")
public class Autista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 15)
    private String sexo;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtnasc;
    @Column(nullable = false, length = 70)
    private String classificacao;
    @Column(length = 30)
    private String escola;
    @Column(nullable = false, length = 3)
    private String mediador;
    @Column(nullable = false)
    private boolean medicamentos;
    @Column(length = 80)
    private String brinquedo;
    @Column(length = 80)
    private String alimento;
    @Column(length = 80)
    private String bebida;
    @Column(length = 80)
    private String atividade;
    @Column(length = 80)
    private String medo;
    @Column(nullable = false)
    private boolean ler;
    @Column(nullable = false)
    private boolean escrever;
    @Column(nullable = false, length = 80)
    private String comunicacao;
    @Column(nullable = false, length = 200)
    private String terapia;
    @Lob
    @Column(length = 65535)
    private String observacao;
    @Lob
    private byte[] foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<br.com.adaca.model.Medicamento> medicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<br.com.adaca.model.Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<br.com.adaca.model.Configuracao> configuracaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<br.com.adaca.model.Responsavel> responsavelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Grafico> graficoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Relatorio> relatorioList;

    public Autista() {
    }

    public Autista(Integer id) {
        this.id = id;
    }

    public Autista(Integer id, String nome, String sexo, Date dtnasc, String classificacao, String mediador, boolean medicamentos, boolean ler, boolean escrever, String comunicacao, String terapia) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.dtnasc = dtnasc;
        this.classificacao = classificacao;
        this.mediador = mediador;
        this.medicamentos = medicamentos;
        this.ler = ler;
        this.escrever = escrever;
        this.comunicacao = comunicacao;
        this.terapia = terapia;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDtnasc() {
        return dtnasc;
    }

    public void setDtnasc(Date dtnasc) {
        this.dtnasc = dtnasc;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getMediador() {
        return mediador;
    }

    public void setMediador(String mediador) {
        this.mediador = mediador;
    }

    public boolean getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(boolean medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getBrinquedo() {
        return brinquedo;
    }

    public void setBrinquedo(String brinquedo) {
        this.brinquedo = brinquedo;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getMedo() {
        return medo;
    }

    public void setMedo(String medo) {
        this.medo = medo;
    }

    public boolean getLer() {
        return ler;
    }

    public void setLer(boolean ler) {
        this.ler = ler;
    }

    public boolean getEscrever() {
        return escrever;
    }

    public void setEscrever(boolean escrever) {
        this.escrever = escrever;
    }

    public String getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(String comunicacao) {
        this.comunicacao = comunicacao;
    }

    public String getTerapia() {
        return terapia;
    }

    public void setTerapia(String terapia) {
        this.terapia = terapia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<br.com.adaca.model.Medicamento> getMedicamentoList() {
        return medicamentoList;
    }

    public void setMedicamentoList(List<Medicamento> medicamentoList) {
        this.medicamentoList = medicamentoList;
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

    public List<br.com.adaca.model.Responsavel> getResponsavelList() {
        return responsavelList;
    }

    public void setResponsavelList(List<Responsavel> responsavelList) {
        this.responsavelList = responsavelList;
    }

    public List<Grafico> getGraficoList() {
        return graficoList;
    }

    public void setGraficoList(List<Grafico> graficoList) {
        this.graficoList = graficoList;
    }

    public List<Relatorio> getRelatorioList() {
        return relatorioList;
    }

    public void setRelatorioList(List<Relatorio> relatorioList) {
        this.relatorioList = relatorioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Autista)) {
            return false;
        }
        Autista other = (Autista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Autista[ id=" + id + " ]";
    }
}
