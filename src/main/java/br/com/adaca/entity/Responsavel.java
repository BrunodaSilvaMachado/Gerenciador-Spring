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
@Table(name = "tb_responsavel")
@NamedQueries({
    @NamedQuery(name = "Responsavel.findAll", query = "SELECT r FROM Responsavel r")
    , @NamedQuery(name = "Responsavel.findById", query = "SELECT r FROM Responsavel r WHERE r.id = :id")
    , @NamedQuery(name = "Responsavel.findByCpf", query = "SELECT r FROM Responsavel r WHERE r.cpf = :cpf")
    , @NamedQuery(name = "Responsavel.findByNome", query = "SELECT r FROM Responsavel r WHERE r.nome = :nome")
    , @NamedQuery(name = "Responsavel.findBySexo", query = "SELECT r FROM Responsavel r WHERE r.sexo = :sexo")
    , @NamedQuery(name = "Responsavel.findByEmail", query = "SELECT r FROM Responsavel r WHERE r.email = :email")
    , @NamedQuery(name = "Responsavel.findByTelefone", query = "SELECT r FROM Responsavel r WHERE r.telefone = :telefone")
    , @NamedQuery(name = "Responsavel.findByCelular", query = "SELECT r FROM Responsavel r WHERE r.celular = :celular")
    , @NamedQuery(name = "Responsavel.findByEndereco", query = "SELECT r FROM Responsavel r WHERE r.endereco = :endereco")
    , @NamedQuery(name = "Responsavel.findByCidade", query = "SELECT r FROM Responsavel r WHERE r.cidade = :cidade")
    , @NamedQuery(name = "Responsavel.findByEstado", query = "SELECT r FROM Responsavel r WHERE r.estado = :estado")})
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 14)
    private String cpf;
    @Basic(optional = false)
    @Column(nullable = false, length = 70)
    private String nome;
    @Basic(optional = false)
    @Column(nullable = false, length = 15)
    private String sexo;
    @Column(length = 45)
    private String email;
    @Basic(optional = false)
    @Column(nullable = false, length = 13)
    private String telefone;
    @Column(length = 14)
    private String celular;
    @Basic(optional = false)
    @Column(nullable = false, length = 60)
    private String endereco;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String cidade;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String estado;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;

    public Responsavel() {
    }

    public Responsavel(Integer id) {
        this.id = id;
    }

    public Responsavel(Integer id, String cpf, String nome, String sexo, String telefone, String endereco, String cidade, String estado) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.adaca.entity.Responsavel[ id=" + id + " ]";
    }
}
