package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class RelatorioDTO implements Serializable {

    private static final long serialVersionUID = 3453327770983384345L;
    private Integer id;
    @NotBlank
    private byte[] relatorio;
    @NotBlank
    @Size(max = 30, message = "Máximo 30 caracteres.")
    private String tiporelatorio;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date datagerado;
    @NotBlank
    private AutistaDTO idautista;
    @NotBlank
    private AdministradorDTO idadministrador;
}
