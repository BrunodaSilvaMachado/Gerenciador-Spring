package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ResultadoDTO implements Serializable {

    private static final long serialVersionUID = -7506303934528967139L;
    private Integer id;
    @NotBlank
    @NumberFormat
    private Integer dicas;
    @NotBlank
    @NumberFormat
    private Integer cliquecerto;
    @NotBlank
    @NumberFormat
    private Integer cliqueerrado;
    @NotBlank
    @Size(max = 65535, message = "Máximo 65535 caracteres.")
    private String mouseclique;
    @NotBlank
    @Size(max = 65535, message = "Máximo 65535 caracteres.")
    private String mousedrag;
    @NotBlank
    @Size(max = 16777215, message = "Máximo 16777215 caracteres.")
    private String mousepos;
    @NotBlank
    @Size(max = 65535, message = "Máximo 65535 caracteres.")
    private String poserrado;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date horainicio;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date horafim;
    @NotBlank
    private SessaoDTO idsessao;
    @NotBlank
    private AtividadeDTO idatividade;
}
