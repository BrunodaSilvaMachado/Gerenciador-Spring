package br.com.adaca.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class SessaoDTO implements Serializable {

    private static final long serialVersionUID = 4345587469761674149L;
    private Integer id;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date datalogin;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date datalogout;
    private List<LabirintoDTO> labirintoList;
    private List<ResultadoDTO> resultadoList;
    @NotBlank
    private AutistaDTO idautista;
    @NotBlank
    private TutorDTO idtutor;
    private List<ConfiguracaoDTO> configuracaoList;
}
