package br.com.adaca.dto;

import br.com.adaca.model.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class AdministradorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String usuario;
    @NotBlank
    @Size(max = 100, message = "Máximo 100 caracteres.")
    private String senha;
    @NotBlank
    private int nivelacesso;
    private List<Grafico> graficoList;
    private List<Relatorio> relatorioList;
}
