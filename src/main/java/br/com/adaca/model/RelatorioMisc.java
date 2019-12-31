package br.com.adaca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RelatorioMisc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "IDSESSAO", nullable = false)
    private Integer idSessao;
    @Column(name = "IDATIVIDADE", nullable = false)
    private Integer idAtividade;
    @Column(nullable = false)
    private Integer dicas;
    @Column(name = "CLIQUECERTO", nullable = false)
    private Integer cliqueCerto;
    @Column(name = "CLIQUEERRADO", nullable = false)
    private Integer cliqueErrado;
    @Column(name = "TEMPO", nullable = false)
    @DateTimeFormat()
    private Double tempo;
    @Column(nullable = false)
    private String classificacao;
    @Column(nullable = false)
    private String nome;
}
