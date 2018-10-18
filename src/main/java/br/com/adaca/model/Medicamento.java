package br.com.adaca.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
@Table(name = "tb_medicamento")
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(length = 50)
    private String tipo;
    @Column(length = 70)
    private String composicao;
    @Column(nullable = false, length = 70)
    private String laboratorio;
    @Column(nullable = false, length = 50)
    private String posologia;
    @Column(length = 100)
    private String observacao;
    @Column(nullable = false, length = 10)
    private String stats;
    @JoinColumn(name = "IDAUTISTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Autista idautista;
}
