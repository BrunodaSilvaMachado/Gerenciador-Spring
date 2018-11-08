package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AdministradorDTO implements Serializable {

    private static final long serialVersionUID = 2323330918806645621L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String usuario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100, message = "Máximo 100 caracteres.")
    private String senha;
    @NumberFormat
    private int nivelacesso;
    private List<GraficoDTO> graficoList;
    private List<RelatorioDTO> relatorioList;
}
