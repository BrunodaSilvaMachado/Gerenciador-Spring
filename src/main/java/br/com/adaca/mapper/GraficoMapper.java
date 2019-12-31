package br.com.adaca.mapper;

import br.com.adaca.dto.GraficoDTO;
import br.com.adaca.model.Grafico;
import br.com.adaca.util.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GraficoMapper extends BaseMapper<Grafico, GraficoDTO> {

}
