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
public class LabirintoDTO implements Serializable {

    private static final long serialVersionUID = -5204466311220667434L;
    private Integer id;
    @NotBlank
    @NumberFormat
    private Integer quanterro;
    @NotBlank
    @Size(max = 300, message = "Máximo 300 caracteres.")
    private String paredes;
    @NotBlank
    private Integer quantdicas;
    @Size(max = 500, message = "Máximo 500 caracteres.")
    private String posicaoerro;
    @Size(max = 500, message = "Máximo 500 caracteres.")
    private String teclaerrada;
    @NotBlank
    private Integer quantbotoes;
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
