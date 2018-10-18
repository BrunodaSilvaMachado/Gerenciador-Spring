package br.com.adaca.dto;

import br.com.adaca.model.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AutistaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 15, message = "Máximo 15 caracteres.")
    private String sexo;
    @NotBlank
    private Date dtnasc;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String classificacao;
    @NotBlank
    @Size(max = 30, message = "Máximo 30 caracteres.")
    private String escola;
    @NotBlank
    @Size(max = 3, message = "Máximo 3 caracteres.")
    private String mediador;
    @NotBlank
    private boolean medicamentos;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String brinquedo;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String alimento;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String bebida;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String atividade;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String medo;
    @NotBlank
    private boolean ler;
    @NotBlank
    private boolean escrever;
    @NotBlank
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String comunicacao;
    @NotBlank
    @Size(max = 200, message = "Máximo 200 caracteres.")
    private String terapia;
    @Size(max  = 65535, message = "Máximo 65535 caracteres.")
    private String observacao;
    private byte[] foto;
    private List<Medicamento> medicamentoList;
    private List<Sessao> sessaoList;
    private List<Configuracao> configuracaoList;
    private List<Responsavel> responsavelList;
    private List<Grafico> graficoList;
    private List<Relatorio> relatorioList;
}
