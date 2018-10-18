package br.com.adaca.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "tb_tutor", uniqueConstraints = {@UniqueConstraint(columnNames = {"USUARIO"})})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 50)
    private String usuario;
    @Column(nullable = false, length = 100)
    private String senha;
    @Column(nullable = false, length = 50)
    private String area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<br.com.adaca.model.Sessao> sessaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtutor")
    private List<br.com.adaca.model.Configuracao> configuracaoList;
}
