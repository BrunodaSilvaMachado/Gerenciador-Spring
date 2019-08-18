package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AutistaDTO implements Serializable {

    private static final long serialVersionUID = -6244209586857135633L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 15, message = "Máximo 15 caracteres.")
    private String sexo;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @NotNull
    private Boolean medicamentos;
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
    @NotNull
    private Boolean ler;
    @NotNull
    private Boolean escrever;
    @NotBlank
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String comunicacao;
    @NotBlank
    @Size(max = 200, message = "Máximo 200 caracteres.")
    private String terapia;
    @Size(max  = 65535, message = "Máximo 65535 caracteres.")
    private String observacao;
    private Byte[] foto;
    private List<MedicamentoDTO> medicamentoList;
    private List<SessaoDTO> sessaoList;
    private List<ConfiguracaoDTO> configuracaoList;
    private List<ResponsavelDTO> responsavelList;
    private List<GraficoDTO> graficoList;
    private List<RelatorioDTO> relatorioList;

    public Integer getId() {
        return id;
    }
}
