package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AtividadeDTO implements Serializable {

    private static final long serialVersionUID = 7183909989218865946L;
    private Integer id;
    @NotBlank
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String nome;
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String classificacao;
    @Max(99)
    @NumberFormat
    private Integer nivel;
    private List<LabirintoDTO> labirintoList;
    private List<ResultadoDTO> resultadoList;
}
