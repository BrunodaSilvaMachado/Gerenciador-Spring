package br.com.adaca.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
@Table(name = "tb_labirinto")
public class Labirinto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private int quanterro;
    @Column(nullable = false, length = 300)
    private String paredes;
    @Column(nullable = false)
    private int quantdicas;
    @Column(length = 500)
    private String posicaoerro;
    @Column(length = 500)
    private String teclaerrada;
    @Column(nullable = false)
    private int quantbotoes;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainicio;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horafim;
    @JoinColumn(name = "IDSESSAO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private br.com.adaca.model.Sessao idsessao;
    @JoinColumn(name = "IDATIVIDADE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Atividade idatividade;
}
