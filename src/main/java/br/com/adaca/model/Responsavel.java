package br.com.adaca.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Willian
 */
@Entity
@Table(name = "tb_responsavel")
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 14)
    private String cpf;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 15)
    private String sexo;
    @Column(length = 45)
    private String email;
    @Column(nullable = false, length = 13)
    private String telefone;
    @Column(length = 14)
    private String celular;
    @Column(nullable = false, length = 60)
    private String endereco;
    @Column(nullable = false, length = 30)
    private String cidade;
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
        return "Responsavel[ id=" + id + " ]";
    }
}
