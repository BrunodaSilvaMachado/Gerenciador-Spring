package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class TutorDTO implements Serializable {

    private static final long serialVersionUID = 8652908057329090714L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String usuario;
    @JsonIgnore
    @NotBlank
    @Size(max = 100, message = "Máximo 100 caracteres.")
    private String senha;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String area;
    private List<SessaoDTO> sessaoList;
    private List<ConfiguracaoDTO> configuracaoList;
}
