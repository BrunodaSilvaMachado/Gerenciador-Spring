package br.com.adaca.dto;

import br.com.adaca.util.BaseId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ConfiguracaoDTO implements Serializable, BaseId {

    private static final long serialVersionUID = 2451058415073638749L;
    private Integer id;
    @NotBlank
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String som;
    @NotBlank
    @Size(max = 80, message = "Máximo 80 caracteres.")
    private String musica;
    @NotNull
    private Boolean dicatempo;
    @NotNull
    private Boolean dicacaminho;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String tipodica;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String comemoracao;
    private Integer tempodica;
    @NotBlank
    @Size(max = 1000, message = "Máximo 1000 caracteres.")
    private String menu;
    @NotNull
    private AutistaDTO idautista;
    @NotNull
    private TutorDTO idtutor;
    @NotNull
    private SessaoDTO idsessao;

    public Integer getId() {
        return id;
    }
}
