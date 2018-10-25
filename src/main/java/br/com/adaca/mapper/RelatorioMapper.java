package br.com.adaca.mapper;

import br.com.adaca.dto.RelatorioDTO;
import br.com.adaca.model.Relatorio;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RelatorioMapper extends BaseMapper<Relatorio, RelatorioDTO> {

}
