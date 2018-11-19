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
@Table(name = "tb_atividade")
public class Atividade implements Serializable {

    private static final long serialVersionUID = -4836465931631553059L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 80)
    private String nome;
    @Column(length = 80)
    private String classificacao;
    private Integer nivel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatividade")
    private List<Labirinto> labirintoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idatividade")
    private List<Resultado> resultadoList;
}
