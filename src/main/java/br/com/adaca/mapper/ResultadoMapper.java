package br.com.adaca.mapper;

import br.com.adaca.dto.ResultadoDTO;
import br.com.adaca.model.Resultado;
import br.com.adaca.util.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResultadoMapper extends BaseMapper<Resultado, ResultadoDTO> {
}
