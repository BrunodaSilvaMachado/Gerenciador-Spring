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
@Builder(toBuilder = true)
@Entity
@Table(name = "tb_administrador")
public class Administrador implements Serializable {

    private static final long serialVersionUID = -6899009903855069451L;
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
    @Column(nullable = false)
    private int nivelacesso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadministrador")
    private List<Grafico> graficoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idadministrador")
    private List<Relatorio> relatorioList;
}
