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
@Table(name = "tb_sessao")
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datalogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datalogout;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Labirinto> labirintoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Resultado> resultadoList;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;
    @JoinColumn(name = "IDTUTOR", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Tutor idtutor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsessao")
    private List<Configuracao> configuracaoList;
}
