package br.com.adaca.model;

import br.com.adaca.util.BaseId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Autista implements Serializable, BaseId {

    private static final long serialVersionUID = -2206202474289214951L;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data é uma informação obrigatória.")
    private Date dtnasc;
    @Column(nullable = false, length = 70)
    private String classificacao;
    @Column(length = 30)
    private String escola;
    @Column(nullable = false, length = 3)
    private String mediador;
    @Column(nullable = false)
    private Boolean medicamentos;
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
    private Boolean ler;
    @Column(nullable = false)
    private Boolean escrever;
    @Column(nullable = false, length = 80)
    private String comunicacao;
    @Column(nullable = false, length = 200)
    private String terapia;
    @Lob
    @Column(length = 65535)
    private String observacao;
    @Lob
    private Byte[] foto;
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
