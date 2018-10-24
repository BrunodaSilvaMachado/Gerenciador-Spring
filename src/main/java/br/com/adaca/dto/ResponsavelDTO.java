package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ResponsavelDTO implements Serializable {

    private static final long serialVersionUID = -3191716321537897117L;
    private Integer id;
    @NotBlank
    @Size(max = 14, message = "Máximo 14 caracteres.")
    private String cpf;
    @NotBlank
    @Size(max = 70, message = "Máximo 70 caracteres.")
    private String nome;
    @NotBlank
    @Size(max = 15, message = "Máximo 15 caracteres.")
    private String sexo;
    @Email
    @Size(max = 45, message = "Máximo 45 caracteres.")
    private String email;
    @NotBlank
    @Size(max = 13, message = "Máximo 13 caracteres.")
    private String telefone;
    @Size(max = 14, message = "Máximo 14 caracteres.")
    private String celular;
    @NotBlank
    @Size(max = 50, message = "Máximo 60 caracteres.")
    private String endereco;
    @NotBlank
    @Size(max = 50, message = "Máximo 30 caracteres.")
    private String cidade;
    @NotBlank
    @Size(max = 30, message = "Máximo 30 caracteres.")
    private String estado;
    @NotBlank
    private AutistaDTO idautista;
}
