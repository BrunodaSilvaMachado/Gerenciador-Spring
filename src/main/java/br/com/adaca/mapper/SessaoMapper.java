package br.com.adaca.mapper;

import br.com.adaca.dto.SessaoDTO;
import br.com.adaca.model.Sessao;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SessaoMapper extends BaseMapper<Sessao, SessaoDTO> {

}
