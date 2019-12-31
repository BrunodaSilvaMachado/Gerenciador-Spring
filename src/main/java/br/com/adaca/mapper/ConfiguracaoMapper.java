package br.com.adaca.mapper;

import br.com.adaca.dto.ConfiguracaoDTO;
import br.com.adaca.model.Configuracao;
import br.com.adaca.util.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfiguracaoMapper extends BaseMapper<Configuracao, ConfiguracaoDTO> {

}
