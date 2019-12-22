package br.com.adaca.dto;

import br.com.adaca.util.BaseId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AdministradorDTO implements Serializable, BaseId {

    private static final long serialVersionUID = 3509815006591019802L;
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
    @NotNull
    @NumberFormat
    private Integer nivelacesso;
    private List<GraficoDTO> graficoList;
    private List<RelatorioDTO> relatorioList;

    public Integer getId() {
        return id;
    }
}
