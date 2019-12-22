package br.com.adaca.model;

import br.com.adaca.util.BaseId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Willian
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "tb_configuracao")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Configuracao implements Serializable, BaseId {

    private static final long serialVersionUID = 2433741048536212977L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 80)
    private String som;
    @Column(nullable = false, length = 80)
    private String musica;
    @Column(nullable = false)
    private Boolean dicatempo;
    @Column(nullable = false)
    private Boolean dicacaminho;
    @Column(nullable = false, length = 50)
    private String tipodica;
    @Column(nullable = false, length = 50)
    private String comemoracao;
    private Integer tempodica;
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
    private Sessao idsessao;
}
