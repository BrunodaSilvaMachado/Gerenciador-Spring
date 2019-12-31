package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class MedicamentoDTO implements Serializable {

    private static final long serialVersionUID = 261925704169274139L;
    private Integer id;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String tipo;
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String composicao;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String laboratorio;
    @NotBlank
    @Size(max = 50, message = "Máximo 50 caracteres.")
    private String posologia;
    @Size(max = 100, message = "Máximo 100 caracteres.")
    private String observacao;
    @NotBlank
    @Size(max = 10, message = "Máximo 10 caracteres.")
    private String stats;
    @NotBlank
    private AutistaDTO idautista;
}
