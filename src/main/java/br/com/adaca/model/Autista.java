package br.com.adaca.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*
* @author Willian
*/
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_autista")
public class Autista implements Serializable {

    private static final long serialVersionUID = -5583032942195618707L;
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
    private List<Medicamento> medicamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Configuracao> configuracaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Responsavel> responsavelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Grafico> graficoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idautista")
    private List<Relatorio> relatorioList;
}
